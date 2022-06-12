package com.company.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonsList implements Serializable {
    private ArrayList<Person> persons;

    public PersonsList() {
        persons = new ArrayList<>();
    }

    public ArrayList<Person> getList() {
        return persons;
    }

    public void add(Person person) {
        persons.add(person);
    }


    public void remove(int numberOfPerson) {
        if (numberOfPerson < persons.size() && numberOfPerson >= 0) {
            persons.remove(numberOfPerson);
        }
    }

    /**
     * Видалення об'єкту по полю surname
     */
    public void deleteBySurname(String surname) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getSurname().equals(surname)) {
                persons.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "list" + persons;
    }
}
