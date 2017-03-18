package com.digzdigital.healthapp.data.model;

import com.digzdigital.healthapp.data.model.projectEnums.BloodGroup;
import com.digzdigital.healthapp.data.model.projectEnums.BloodType;

import java.util.ArrayList;
import java.util.Date;

import dagger.Module;

/**
 * Created by Digz on 09/03/2017.
 */

public class Mother {
    private ArrayList<Child> children;
    private int age;
    private BloodGroup bloodGroup;
    private BloodType bloodType;
    private String dateOfBirth;
    private boolean hasHadCS;
    private String name;

    public Mother(){

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

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public int noOfChildren(){
        return children.size();
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

    public boolean isHasHadCS() {
        return hasHadCS;
    }

    public void setHasHadCS(boolean hasHadCS) {
        this.hasHadCS = hasHadCS;
    }
}
