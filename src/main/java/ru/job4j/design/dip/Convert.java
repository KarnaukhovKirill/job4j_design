package ru.job4j.design.dip;

/**
 * Класс Main содержит в себе более низкоуровневый класс convert, который работает только с типом A.
 * Следует выделить абстракцию
 */
public class Convert {
    public boolean convertAtoB() {
        return false;
    }

    public static class Main {
        private static Convert convert = new Convert();

        public static void main(String[] args) {
            var result = convert.convertAtoB();
        }
    }
}
