package ru.job4j.design.dip;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Имеем модель данных Person. Имеем хранилище этих персон ListPerson и класс, который работает с этим хранилищем.
 * Ошибка принципа DIP состоит в том, что модуль SearchByAge теперь зависит от ListPerson. И метод search теперь жёстко
 * завязан на работу именно с List.
 * Решением такой ситуации может служить:
 * 1. Создание абстракции storage. И дальнейшая работа в Search именно с абстракцией.
 * 2. Перенос метода search в интерфейс и в аргументы принимать предикат.
 */
public class SearchByAge {
    List<ListPerson.Person> storage;

    public SearchByAge(List<ListPerson.Person> storage) {
        this.storage = storage;
    }

    public List<ListPerson.Person> search(int age) {
        return storage.stream().filter(person -> person.age < age).collect(Collectors.toList());
    }

    public class ListPerson {
        List<Person> persons;

        public ListPerson(List<Person> persons) {
            this.persons = persons;
        }



        public class Person {
            protected String name;
            protected int age;
        }
    }
}
