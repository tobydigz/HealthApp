package com.digzdigital.healthapp.data.model.appointment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Digz on 10/03/2017.
 */

public class Doctor implements Parcelable{
    private String id;
    private String name;

    public Doctor() {
    }

    public Doctor(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(id);
        parcel.writeString(name);
    }

    public static final Parcelable.Creator<Doctor> CREATOR = new Parcelable.Creator<Doctor>(){
        @Override
        public Doctor createFromParcel(Parcel parcel){
            return new Doctor(parcel);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }

    };

    @Override
    public int describeContents(){
        return hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
