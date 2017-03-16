package com.digzdigital.healthapp.data.model.projectEnums;

/**
 * Created by Digz on 09/03/2017.
 */

public enum BloodGroup {
    O_POSITIVE("O+"),
    A_POSITIVE("A+"),
    B_POSITIVE("B+"),
    AB_POSITIVE("AB+"),
    O_NEGATIVE("O-"),
    A_NEGATIVE("A-"),
    B_NEGATIVE("B-"),
    AB_NEGATIVE("AB-");

    private final String name;

    BloodGroup(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
