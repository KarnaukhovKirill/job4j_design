package ru.job4j.jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        var path = new File("app.properties").getAbsolutePath();
        Properties properties = new Properties();
        properties.load(new FileReader(path));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("FirstTable");
        System.out.println(getTableScheme(tableEditor.connection, "FirstTable"));
        tableEditor.dropTable("FirstTable");
        tableEditor.createTable("SecondTable");
        tableEditor.addColumn("SecondTable", "Name", "varchar(255)");
        tableEditor.addColumn("SecondTable", "SecondName", "varchar(255)");
        tableEditor.dropColumn("SecondTable", "SecondName");
        tableEditor.renameColumn("SecondTable", "Name", "Имя");
        System.out.println(getTableScheme(tableEditor.connection, "SecondTable"));
        tableEditor.dropTable("SecondTable");
        tableEditor.close();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            connection = DriverManager.getConnection(properties.getProperty("hibernate.connection.url"),
                    properties.getProperty("hibernate.connection.username"),
                    properties.getProperty("hibernate.connection.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        var sql = String.format("create table if not exists %s();", tableName);
        execute(sql);
    }

    public void dropTable(String tableName) {
        var sql = String.format("drop table if exists %s;", tableName);
        execute(sql);
        System.out.printf("Таблица %s удалена", tableName);
        System.out.print(System.lineSeparator());
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "alter table " + tableName + " add column " + columnName + " " + type;
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        var sql = "alter table " + tableName + " drop column " + columnName;
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        var sql = "alter table " + tableName + " rename column " + columnName + " to " + newColumnName;
        execute(sql);
    }


    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
