package com.digzdigital.healthapp.model.data;

import com.digzdigital.healthapp.R;

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
    private int imageResId;

    public Food (){

    }

    public Food(String description, int type, int day){
        this.description = description;
        this.type = type;
        this.day = day;
    }

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
                type = "Snack";
                break;
            case 2:
                type = "Lunch";
                break;
            case 3:
                type = "Snack";
                break;
            case 4:
                type = "Dinner";
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

    public int getImageResId() {
        int resId = 0;
        switch (type){
            case 0:
                resId = R.mipmap.breakfast;
                break;
            case 1:
            case 3:
                resId = R.mipmap.snacks;
                break;
            case 2:
                resId = R.mipmap.lunch;
                break;
            case 4:
                resId = R.mipmap.dinner;
                break;

        }
        return resId;
    }
}
