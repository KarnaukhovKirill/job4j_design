package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ReportEngineTest {
    String ls = "\n";

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired().getTime() + ";"
                + worker.getFired().getTime() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenProgrammerGenerator() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker01 = new Employee("Ivan", now, now, 100);
        store.add(worker01);
        Report engine = new ReportProgrammers(store);
        String expected = "<html><body>"
                            + "<h1>Список работников</h1>"
                            + "<p>Name; Hired; Fired; Salary;</p><p>"
                            + worker01.getName() + ";"
                            + worker01.getHired().getTime() + ";"
                            + worker01.getFired().getTime() + ";"
                            + worker01.getSalary() + ";"
                            + "</p></body></html>";
        assertThat(engine.generate(em -> true), is(expected));
    }

    @Test
    public void whenHRGenerator() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker01 = new Employee("Ivan", now, now, 100);
        Employee worker02 = new Employee("Kirill", now, now, 1000);
        store.add(worker01);
        store.add(worker02);
        Report engine = new ReportHR(store);
        String expect = "Name; Salary;"
                + System.lineSeparator()
                + worker02.getName() + ";"
                + worker02.getSalary() + ";"
                + System.lineSeparator()
                + worker01.getName() + ";"
                + worker01.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenBookkeepersGenerator() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker01 = new Employee("Ivan", now, now, 100);
        store.add(worker01);
        Report engine = new ReportBookkeepers(store);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + worker01.getName() + ";"
                + worker01.getHired().getTime() + ";"
                + worker01.getFired().getTime() + ";"
                + 7300.0 + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenXMLReportGenerator() {
        Calendar firstDate = Calendar.getInstance();
        firstDate.setTimeZone(TimeZone.getDefault());
        firstDate.set(2018, Calendar.JANUARY, 1);
        Calendar secondDate = Calendar.getInstance();
        secondDate.setTimeZone(TimeZone.getDefault());
        secondDate.set(2021, Calendar.DECEMBER, 12);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        MemStore store = new MemStore();
        Employee worker01 = new Employee("Kirill",
                firstDate,
                secondDate,
                100.00D);
        Employee worker02 = new Employee("Ivan",
                firstDate,
                secondDate,
                150D);
        store.add(worker01);
        store.add(worker02);
        Report engine = new XMLReport(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + ls
                + "<Employees>" + ls
                + "    <employees>" + ls
                + "        <fired>" + dateFormat.format(worker01.getFired().getTime()) + "</fired>" + ls
                + "        <hired>" + dateFormat.format(worker01.getHired().getTime()) + "</hired>" + ls
                + "        <name>Kirill</name>" + ls
                + "        <salary>100.0</salary>" + ls
                + "    </employees>"  + ls
                + "    <employees>"  + ls
                + "        <fired>" + dateFormat.format(worker02.getFired().getTime()) + "</fired>" + ls
                + "        <hired>" + dateFormat.format(worker02.getHired().getTime()) + "</hired>" + ls
                + "        <name>Ivan</name>" + ls
                + "        <salary>150.0</salary>" + ls
                + "    </employees>" + ls
                + "</Employees>" + ls;
        assertThat(engine.generate(s -> true), is(expect));
    }

    @Test
    public void whenJSONReportGenerator() {
        MemStore store = new MemStore();
        Employee worker01 = new Employee("Kirill",
                Calendar.getInstance(),
                new GregorianCalendar(2021, Calendar.DECEMBER, 12),
                100.00D);
        store.add(worker01);
        Report engine = new JSONReport(store);
        var lib = new GsonBuilder().create();
        String expect = lib.toJson(store.findBy(s -> true));
        assertThat(engine.generate(s -> true), is(expect));
    }
}