package ru.job4j.ood.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import ru.job4j.design.srp.*;

import java.util.Calendar;

public class ReportEngineTest {

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
}