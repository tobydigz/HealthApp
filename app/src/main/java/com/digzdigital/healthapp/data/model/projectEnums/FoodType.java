package com.digzdigital.healthapp.data.model.projectEnums;

/**
 * Created by Digz on 09/03/2017.
 */

public enum FoodType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACKS("Snacks");

    private final String foodTypeName;

    FoodType(String foodTypeName){
        this.foodTypeName = foodTypeName;
    }

    public String foodTypeName(){
        return foodTypeName;
    }

}
