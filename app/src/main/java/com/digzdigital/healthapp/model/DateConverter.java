package com.digzdigital.healthapp.model;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Digz on 05/12/2016.
 *
 */

public class DateConverter {


    public DateConverter() {
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

    public String getFullDate(Date date) {
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
        String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
        String day = (String) android.text.format.DateFormat.format("dd", date); //20
        String ordinality = ordinal(Integer.parseInt(day));
        return dayOfTheWeek + ", " + day + ordinality + " " + stringMonth + " " + year;
    }

    public String getShortDate(Date date) {
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
        String day = (String) android.text.format.DateFormat.format("dd", date); //20
        return stringMonth.substring(0, 2) + " " + day;
    }

    public String getTime(Date date) {
        DateFormat localDateFormat = DateFormat.getTimeInstance();
        return localDateFormat.format(date);
    }
}
