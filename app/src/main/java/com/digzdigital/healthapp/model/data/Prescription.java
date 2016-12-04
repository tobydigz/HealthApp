package com.digzdigital.healthapp.model.data;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Digz on 04/12/2016.
 *
 */

public class Prescription extends RealmObject {

    @PrimaryKey
    private String id;
    private Date startDate;
    private Date endDate;
    private Drug drug;
    private RealmList<EventDate> eventDates;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return turnDateToString(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return turnDateToString(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public RealmList<EventDate> getEventDates() {
        return eventDates;
    }

    public void setEventDates(RealmList<EventDate> eventDates) {
        this.eventDates = eventDates;
    }

    public String getDrugName() {
        return drug.getName();
    }

    public String getDrugId() {
        return drug.getId();
    }

    public String getNextDoseDate(Date today) {
        Date tempDate = new Date();

        for (int i = 0; i < eventDates.size(); i++) {
            tempDate = eventDates.get(i).getDate();
            if (today.after(tempDate))
                return turnDateToString(tempDate);
        }
        return turnDateToString(tempDate);
    }

    private String turnDateToString(Date date) {
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
        String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
        String day = (String) android.text.format.DateFormat.format("dd", date); //20
        String ordinality = ordinal(Integer.parseInt(day));
        return dayOfTheWeek + ", " + day + ordinality + " " + stringMonth + " " + year;
    }
}
