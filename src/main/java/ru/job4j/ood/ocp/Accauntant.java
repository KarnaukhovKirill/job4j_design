package ru.job4j.ood.ocp;

public class Accauntant {
    private HandMethod method = new HandMethod();
/*
Чтобы бухгалтер стал считать зарплату с помощью калькулятора, нужно переделывать класс.
Класс жёстко связан с методом подсчёта зарплаты
 */
    public String calculate() {
        return method.getHandMethod();
    }
}
