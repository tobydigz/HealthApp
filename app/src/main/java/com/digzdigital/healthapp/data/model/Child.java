package com.digzdigital.healthapp.data.model;

import com.digzdigital.healthapp.data.model.projectEnums.BloodGroup;
import com.digzdigital.healthapp.data.model.projectEnums.BloodType;

import java.util.ArrayList;

/**
 * Created by Digz on 09/03/2017.
 */

public class Child {
    private String name;
    private int age;
    private String dateOfBirth;
    private BloodGroup bloodGroup;
    private BloodType bloodType;

    public Child(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup.getName();
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodType() {
        return bloodType.name();
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
