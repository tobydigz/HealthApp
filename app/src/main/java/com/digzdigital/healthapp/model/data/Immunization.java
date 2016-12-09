package com.digzdigital.healthapp.model.data;

import com.digzdigital.healthapp.model.DateConverter;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Digz on 06/12/2016.
 */

public class Immunization extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String location;
    private Date date;
    @Ignore
    private DateConverter dateConverter = new DateConverter();

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate(){
        return dateConverter.getShortDate(date);
    }

    public String getTime(){
        return dateConverter.getTime(date);
    }

    public String getFullDate(){
        return dateConverter.getFullDate(date);
    }

}
