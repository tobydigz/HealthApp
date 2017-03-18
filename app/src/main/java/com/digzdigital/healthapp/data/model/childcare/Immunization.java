package com.digzdigital.healthapp.data.model.childcare;

import com.digzdigital.healthapp.data.model.DateConverter;

import java.util.Date;



public class Immunization {

    private String id;
    private String name;
    private long month;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getMonth(){
        return month;
    }


}
