package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportProgrammers implements Report {
    private Store store;

    public ReportProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body>")
                .append("<h1>Список работников</h1>")
                .append("<p>Name; Hired; Fired; Salary;</p>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>");
        }
        text.append("</body></html>");
        return text.toString();
    }
}
