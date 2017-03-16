package com.digzdigital.healthapp.data.model;

import java.text.DateFormat;
import java.util.Date;


/**
 * Created by Digz on 04/12/2016.
 */

public class EventDate{

    private Date date;

    public EventDate() {

    }

    private static String ordinal(int i) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public String getDay() {
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
        String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
        String day = (String) android.text.format.DateFormat.format("dd", date); //20
        String ordinality = ordinal(Integer.parseInt(day));
        return dayOfTheWeek + ", " + day + ordinality + " " + stringMonth + " " + year;
    }

    public String getTime() {
        DateFormat localDateFormat = DateFormat.getTimeInstance();
        return localDateFormat.format(date);
    }


}
