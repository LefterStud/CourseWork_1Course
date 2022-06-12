package com.company.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Comparator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private String country;
    private String kindOfSport;
    private int registrationNumber;
    private int shownResult;
    private int ratingPlace;

    public Person(String name, String surname, String patronymic, String country, String kindOfSport, int shownResult) {
        this.setName(name);
        this.setSurname(surname);
        this.setPatronymic(patronymic);
        this.setCountry(country);
        this.setKindOfSport(kindOfSport);
        this.setShownResult(shownResult);
    }

    public Person() {
    }

    /**
     * Перевірка/виправлення та передача значення у поле "name" об'єкта.
     */
    public void setName(String name) {
        this.name = name.strip();
        for (int i = 0; i < this.name.length(); i++) {
            if (!Character.isAlphabetic(this.name.charAt(i))) {
                this.name = "Incorrect data";
            }
        }
        this.name = ("" + this.name.charAt(0)).toUpperCase() + this.name.substring(1).toLowerCase();
    }

    /**
     * Перевірка/виправлення та передача значення у поле "surname" об'єкта.
     */
    public void setSurname(String surname) {
        this.surname = surname.strip();
        for (int i = 0; i < this.surname.length(); i++) {
            if (!Character.isAlphabetic(this.surname.charAt(i))) {
                this.surname = "Incorrect data";
            }
        }
        this.surname = ("" + this.surname.charAt(0)).toUpperCase() + this.surname.substring(1).toLowerCase();
    }

    /**
     * Перевірка/виправлення та передача значення у поле "patronymic" об'єкта.
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic.strip();
        for (int i = 0; i < this.patronymic.length(); i++) {
            if (!Character.isAlphabetic(this.patronymic.charAt(i))) {
                this.patronymic = "Incorrect data";
            }
        }
        this.patronymic = ("" + this.patronymic.charAt(0)).toUpperCase() + this.patronymic.substring(1).toLowerCase();
    }

    /**
     * Перевірка/виправлення та передача значення у поле "country" об'єкта.
     */
    public void setCountry(String country) {
        this.country = country.strip();
        for (int i = 0; i < this.country.length(); i++) {
            if (!(Character.isAlphabetic(this.country.charAt(i)) || (this.country.charAt(i) == '-') || (this.country.charAt(i) == ' '))) {
                this.country = "Incorrect data";
            }
        }
        this.country = ("" + this.country.charAt(0)).toUpperCase() + this.country.substring(1).toLowerCase();
        for (int i = 0; i < this.country.length(); i++) {
            if ((this.country.charAt(i) == '-') || (this.country.charAt(i) == ' ')) {
                this.country = this.country.substring(0, i + 1) + ("" + this.country.charAt(i + 1)).toUpperCase() + this.country.substring(i + 2);
            }
        }
    }

    /**
     * Перевірка/виправлення та передача значення у поле "kindOfSport" об'єкта.
     */
    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport.strip();
        for (int i = 0; i < this.kindOfSport.length(); i++) {
            if (!Character.isAlphabetic(this.kindOfSport.charAt(i)) || (this.kindOfSport.charAt(i) == '-')) {
                this.kindOfSport = "Incorrect data";
            }
        }
        this.kindOfSport = ("" + this.kindOfSport.charAt(0)).toUpperCase() + this.kindOfSport.substring(1).toLowerCase();
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;

    }

    public void setShownResult(int shownResult) {
        this.shownResult = shownResult;
    }

    public void setRatingPlace(int ratingPlace) {
        this.ratingPlace = ratingPlace;
    }

    /**
     * Розрахунок регістраційного номера спортсмена
     */
    public static void calculateRegistrationNumber(PersonsList personsList) {
        for (int i = 0; i < personsList.getList().size(); i++) {
            personsList.getList().get(i).setRegistrationNumber(i + 1);
        }
    }

    /**
     * Розрахунок призового місця спортсмена
     */
    public static void calculateRatingPlace(PersonsList personsList) {
        personsList.getList().sort(Person.byShownResultInv);
        personsList.getList().sort(Person.byKindOfSport);
        String tempSport = personsList.getList().get(0).getKindOfSport();
        int tempPlace = 1;
        personsList.getList().get(0).setRatingPlace(tempPlace);
        for (int i = 1; i < personsList.getList().size(); i++) {
            if (personsList.getList().get(i).getKindOfSport().equals(tempSport)) {
                tempPlace++;
                personsList.getList().get(i).setRatingPlace(tempPlace);
            } else {
                tempPlace = 1;
                personsList.getList().get(i).setRatingPlace(tempPlace);
                tempSport = personsList.getList().get(i).getKindOfSport();
            }
        }
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public int getShownResult() {
        return shownResult;
    }

    public int getRatingPlace() {
        return ratingPlace;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", patronymic='" + getPatronymic() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", kindOfSport='" + getKindOfSport() + '\'' +
                ", registrationNumber=" + getRegistrationNumber() +
                ", shownResult=" + getShownResult() +
                ", place=" + getRatingPlace() +
                '}' + System.lineSeparator();
    }

    /**
     * Компаратори для сортування списку
     */
    public static Comparator<Person> byName = Comparator.comparing(o -> o.name);
    public static Comparator<Person> bySurname = Comparator.comparing(o -> o.surname);
    public static Comparator<Person> byPatronymic = Comparator.comparing(o -> o.patronymic);
    public static Comparator<Person> byKindOfSport = Comparator.comparing(o -> o.kindOfSport);
    public static Comparator<Person> byCountry = Comparator.comparing(o -> o.country);
    public static Comparator<Person> byShownResultInv = (o1, o2) -> o1.shownResult < o2.shownResult ? 1 : o1.shownResult > o2.shownResult ? -1 : 0;
    public static Comparator<Person> byRegistrationNumber = (o1, o2) -> o1.registrationNumber > o2.registrationNumber ? 1 : o1.registrationNumber < o2.registrationNumber ? -1 : 0;
}
