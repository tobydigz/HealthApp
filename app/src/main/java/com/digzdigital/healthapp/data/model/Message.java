package com.digzdigital.healthapp.data.model;

/**
 * Created by Digz on 23/03/2017.
 */

public class Message {

    private String message;
    private String hospital;
    private String doctor;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
