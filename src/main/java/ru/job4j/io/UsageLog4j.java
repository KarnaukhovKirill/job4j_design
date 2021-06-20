package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        boolean goodMan = true;
        double weigth = 78.9;
        long lvl = 80L;
        char favoriteNumber = '7';
        byte countCountry = 100;
        short population = 30000;

        LOG.debug("int : {}, "
                + "boolean : {}, "
                + "double : {}, "
                + "long : {} "
                + "char : {} "
                + "byte : {} "
                + "short : {} ",
                age, goodMan, weigth, lvl, favoriteNumber, countCountry, population);
    }
}
