package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairIsOk() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenValueIsEmpty() {
            String path = "logEmpty.properties";
            Config config = new Config(path);
            config.load();
            assertThat(config.value("YayaDingDong"), is("Value is empty"));
    }

    @Test
    public void whenTextWithSpaceAndComments() {
        String path = "logSpace.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("JavaIsBest?"), is("Yes"));
    }

    @Test
    public void whenKeyIsWrong() {
        String path = "logSpace.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("JavaIsBest?"), is("Yes"));
        assertNull(config.value("WrongKey"));
    }
}