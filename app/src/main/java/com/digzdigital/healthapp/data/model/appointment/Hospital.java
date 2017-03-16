package com.digzdigital.healthapp.data.model.appointment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Digz on 10/03/2017.
 */

public class Hospital implements Parcelable{
    private String id;
    private String name;
    private String address;

    public Hospital() {
    }

    public Hospital(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.address = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(address);
    }

    public static final Parcelable.Creator<Hospital> CREATOR = new Parcelable.Creator<Hospital>(){
        @Override
        public Hospital createFromParcel(Parcel parcel){
            return new Hospital(parcel);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
