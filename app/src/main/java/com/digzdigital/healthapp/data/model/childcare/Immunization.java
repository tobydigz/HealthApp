package com.digzdigital.healthapp.data.model.childcare;

import com.digzdigital.healthapp.data.model.DateConverter;

import java.util.Date;



public class Immunization {

    private int id;
    private String name;
    private int month;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth(){
        return month;
    }


}
