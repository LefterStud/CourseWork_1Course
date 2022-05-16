package com.company.classes;

import java.time.LocalDate;

public class utilClass {
    public static LocalDate getCorrectDate(LocalDate date) {
        return (date.isBefore(LocalDate.now()) ? date : LocalDate.of(0, 0, 0));
    }
}
