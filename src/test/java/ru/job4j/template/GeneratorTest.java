package ru.job4j.template;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenInputDataIsValid() throws Exception {
        Generator generator = new EntryGenerator();
        String template = "I am a ${name}, who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "You");
        var rsl = generator.produce(template, args);
        assertThat("I am Petr, who are You", is(rsl));
    }

    @Ignore
    @Test(expected = Exception.class)
    public void whenKeysIsWrong() throws Exception {
        Generator generator = new EntryGenerator();
        String template = "I am a ${name}. ${mood} to meet you! Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "You");
        var rsl = generator.produce(template, args);
    }

    @Ignore
    @Test(expected = Exception.class)
    public void whenKeysIsMoreThenTemplate() throws Exception {
        Generator generator = new EntryGenerator();
        String template = "I am a ${name}. Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "You");
        args.put("mood", "Bad");
        var rsl = generator.produce(template, args);
    }
}