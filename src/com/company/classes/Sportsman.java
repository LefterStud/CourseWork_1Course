package com.company.classes;

import java.time.LocalDate;

public class Sportsman extends Person{
    protected String kindOfSport;
    protected int registrationNumber;
    protected double shownResult;
    protected int place;

    public Sportsman(String name, String surname, String patronymic, LocalDate dateOfBirth, String country, String kindOfSport, int registrationNumber, double shownResult, int place) {
        super(name, surname, patronymic, dateOfBirth, country);
        this.setKindOfSport(kindOfSport);
        this.setRegistrationNumber(registrationNumber);
        this.setShownResult(shownResult);
        this.setPlace(place);
    }

    //todo
    public void setKindOfSport(String kindOfSport) {
        this.kindOfSport = kindOfSport;
    }
    //todo
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    //todo
    public void setShownResult(double shownResult) {
        this.shownResult = shownResult;
    }
    //todo
    public void setPlace(int place) {
        this.place = place;
    }





    public String getKindOfSport() {
        return kindOfSport;
    }
    public int getRegistrationNumber() {
        return registrationNumber;
    }
    public double getShownResult() {
        return shownResult;
    }
    public int getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                "kindOfSport='" + getKindOfSport() + '\'' +
                ", registrationNumber=" + getRegistrationNumber() +
                ", shownResult=" + getShownResult()+
                ", place=" + getPlace() +
                '}';
    }
}
