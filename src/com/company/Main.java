package com.company;

import com.company.classes.Person;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("john", "Smith","OlexandroVich ", LocalDate.of(2003,5,24), "Ukraine");
        System.out.println(person1);

    }
}
