package com.digzdigital.healthapp.data.model.mothercare;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.projectEnums.DayType;
import com.digzdigital.healthapp.data.model.projectEnums.FoodType;


public class Food {

    private String description;
    private FoodType foodType;
    private DayType day;

    private int imageResId;

    public Food (){

    }

    public Food(String description, FoodType foodType, DayType day){
        this.description = description;
        this.foodType = foodType;
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {

        return foodType.foodTypeName();
    }

    public void setType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getDay() {
        return day.dayName();
    }

    public void setDay(DayType day) {
        this.day = day;
    }

    public int getImageResId() {
        int resId = 0;
        switch (foodType){
            case BREAKFAST:
                resId = R.mipmap.breakfast;
                break;
            case SNACKS:
                resId = R.mipmap.snacks;
                break;
            case LUNCH:
                resId = R.mipmap.lunch;
                break;
            case DINNER:
                resId = R.mipmap.dinner;
                break;

        }
        return resId;
    }
}
