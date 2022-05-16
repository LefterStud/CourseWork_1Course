package com.company.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    public static final int MIN_AGE = 18;
    protected String name;
    protected String surname;
    protected String patronymic;
    protected String country;
    protected LocalDate dateOfBirth;

    public Person(String name, String surname, String patronymic, LocalDate dateOfBirth, String country) {
        this.setName(name);
        this.setSurname(surname);
        this.setPatronymic(patronymic);
        this.setDateOfBirth(dateOfBirth);
        this.setCountry(country);
    }

    public void setName(String name) {
        this.name = name.strip();
        for (int i = 0; i < this.name.length(); i++) {
            if (!Character.isAlphabetic(this.name.charAt(i))) {
                this.name = "Incorrect data";
            }
        }
        this.name = ("" + this.name.charAt(0)).toUpperCase() + this.name.substring(1).toLowerCase();
    }

    public void setSurname(String surname) {
        this.surname = surname.strip();
        for (int i = 0; i < this.surname.length(); i++) {
            if (!Character.isAlphabetic(this.surname.charAt(i))) {
                this.surname = "Incorrect data";
            }
        }
        this.surname = ("" + this.surname.charAt(0)).toUpperCase() + this.surname.substring(1).toLowerCase();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic.strip();
        for (int i = 0; i < this.patronymic.length(); i++) {
            if (!Character.isAlphabetic(this.patronymic.charAt(i))) {
                this.patronymic = "Incorrect data";
            }
        }
        this.patronymic = ("" + this.patronymic.charAt(0)).toUpperCase() + this.patronymic.substring(1).toLowerCase();
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = utilClass.getCorrectDate(dateOfBirth);
        if(Period.between(dateOfBirth, LocalDate.now()).getYears()<MIN_AGE){
            dateOfBirth=LocalDate.of(0, 0, 0);
        }
    }

    public void setCountry(String country) {
        this.country = country.strip();
        for (int i = 0; i < this.country.length(); i++) {
            if (!(Character.isAlphabetic(this.country.charAt(i))||(this.country.charAt(i)=='-'))) {
                this.country = "Incorrect data";
            }
        }
        this.country = ("" + this.country.charAt(0)).toUpperCase() + this.country.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getCountry() {
        return country;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", patronymic='" + getPatronymic() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                '}';
    }
}
