package com.digzdigital.healthapp.data.model.projectEnums;

/**
 * Created by Digz on 09/03/2017.
 */

public enum DayType {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("saturday"),
    SUNDAY("Sunday");

    private final String dayName;

    DayType(String dayName){
        this.dayName = dayName;
    }

    public String dayName(){
        return dayName;
    }
}
