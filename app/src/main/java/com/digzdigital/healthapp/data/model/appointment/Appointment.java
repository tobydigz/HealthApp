package com.digzdigital.healthapp.data.model.appointment;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Digz on 10/03/2017.
 */

public class Appointment implements Parcelable{

    private String key;
    private String doctorName;
    private String doctorId;
    private String patientName;
    private String motherId;
    private String hospitalName;
    private String hospitalId;
    private String hospitalAddress;
    private Date date = new Date();
    private int approved;

    public Appointment() {
    }

    public Appointment(Parcel in){
        this.key = in.readString();
        this.doctorName = in.readString();
        this.doctorId = in.readString();
        this.patientName = in.readString();
        this.motherId = in.readString();
        this.hospitalName = in.readString();
        this.hospitalId = in.readString();
        this.hospitalAddress = in.readString();
        this.date.setTime(in.readLong());
        this.approved = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(key);
        parcel.writeString(doctorId);
        parcel.writeString(patientName);
        parcel.writeString(motherId);
        parcel.writeString(hospitalName);
        parcel.writeString(hospitalId);
        parcel.writeString(hospitalAddress);
        parcel.writeLong(date.getTime());
        parcel.writeInt(approved);
    }

    public static final Parcelable.Creator<Appointment> CREATOR = new Parcelable.Creator<Appointment>(){
        @Override
        public Appointment createFromParcel(Parcel parcel){
            return new Appointment(parcel);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    @Override
    public int describeContents(){
        return hashCode();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        if (approved == 1) return "Approved";
        return "Unapproved";
    }

    public void setApproved(boolean approved) {
        if (approved)this.approved = 1;
        else this.approved = 0;
    }
}
