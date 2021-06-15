package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    String ln = System.lineSeparator();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.print("200 10:56:01" + ln
                    + "500 10:57:01" + ln
                    + "400 10:58:01" + ln
                    + "200 10:59:01" + ln
                    + "500 11:01:02" + ln
                    + "200 11:02:02" + ln
                    + "200 10:56:01" + ln
                    + "500 10:57:01" + ln
                    + "400 10:58:01" + ln
                    + "500 10:59:01" + ln
                    + "400 11:01:02" + ln
                    + "200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01"
                                        + "11:01:02;11:02:02"
                                        + "10:57:01;11:02:02"));
    }

}