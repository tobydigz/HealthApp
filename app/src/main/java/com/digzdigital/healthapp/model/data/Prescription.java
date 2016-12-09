package com.digzdigital.healthapp.model.data;

import com.digzdigital.healthapp.model.DateConverter;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Digz on 04/12/2016.
 */

public class Prescription extends RealmObject {

    public Prescription prescription;
    @PrimaryKey
    private String id;
    private Date startDate;
    private Date endDate;
    private Drug drug;
    private String amount;
    private RealmList<EventDate> eventDates;
    @Ignore
    private DateConverter dateConverter = new DateConverter();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return dateConverter.getFullDate(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return dateConverter.getFullDate(endDate);
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

    private Date getNextDoseDateObject(Date today) {
        Date tempDate = new Date();

        for (int i = 0; i < eventDates.size(); i++) {
            tempDate = eventDates.get(i).getDate();
            if (today.after(tempDate))
                return tempDate;
        }
        return tempDate;
    }

    public String getNextDoseDate(Date now) {
        Date nextDoseDate = getNextDoseDateObject(now);
        return dateConverter.getShortDate(nextDoseDate);
    }

    public String getNextDoseTime(Date now){
        Date nextDoseTime = getNextDoseDateObject(now);
        return dateConverter.getTime(nextDoseTime);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getImageResId() {
        return drug.getImageResId();
    }
}
