package com.digzdigital.healthapp.model.data;

import io.realm.RealmObject;

/**
 * Created by Digz on 08/12/2016.
 */

public class Food extends RealmObject{

    private String description;
    /*Range
     * breakfast = 0
      * lunch = 1
      * dinner = 2
      * snacks = 3*/
    private int type;
    private int day;/*Ranges from 0 to 6*/
    private int trimester; /*Ranges from 0 to 2*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        String type = null;
        switch (this.type){
            case 0:
                type = "Breakfast";
            break;
            case 1:
                type = "Lunch";
                break;
            case 2:
                type = "Dinner";
                break;
            case 3:
                type = "Snacks";
                break;

        }
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDay() {
        String day = null;
        switch (this.day){
            case 0:
                day = "Monday";
                break;
            case 1:
                day = "Tuesday";
                break;
            case 2:
                day = "Wednesday";
                break;
            case 3:
                day = "Thursday";
                break;
            case 4:
                day = "Friday";
                break;
            case 5:
                day = "Saturday";
                break;
            case 6:
                day = "Sunday";
                break;
        }
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTrimester() {
        String trimester = null;
        switch (this.trimester){
            case 0:
                trimester="First Trimester";
                break;
            case 1:
                trimester="Second Trimester";
                break;
            case 2:
                trimester="Third Trimester";
                break;
        }
        return trimester;
    }

    public void setTrimester(int trimester) {
        this.trimester = trimester;
    }
}
