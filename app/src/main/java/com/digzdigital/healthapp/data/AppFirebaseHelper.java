package com.digzdigital.healthapp.data;

import com.digzdigital.healthapp.data.model.Child;
import com.digzdigital.healthapp.data.model.Mother;
import com.digzdigital.healthapp.data.model.appointment.Appointment;
import com.digzdigital.healthapp.data.model.appointment.Doctor;
import com.digzdigital.healthapp.data.model.appointment.Hospital;
import com.digzdigital.healthapp.data.model.childcare.Immunization;
import com.digzdigital.healthapp.data.model.mothercare.AntenatalTest;
import com.digzdigital.healthapp.eventbus.EventType;
import com.digzdigital.healthapp.eventbus.FirebaseEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class AppFirebaseHelper implements FirebaseHelper {
    private DatabaseReference databaseReference;
    private Mother mother;
    private ArrayList<Child> children;
    private ArrayList<Hospital> hospitals;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<AntenatalTest> antenatalTests;
    private ArrayList<Immunization> immunizations;

    public AppFirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public DatabaseReference queryForAntenatalTests() {
       return databaseReference.child("tests");
    }

    @Override
    public ArrayList<AntenatalTest> getAntenatalTests() {
        return antenatalTests;
    }

    @Override
    public DatabaseReference queryForImmunizations() {
        return databaseReference.child("immunizations");
    }

    @Override
    public ArrayList<Immunization> getImmunizations() {
        return immunizations;
    }

    @Override
    public DatabaseReference queryForMother(String id) {
        return databaseReference.child("mothers").child(id);
    }

    @Override
    public Mother getMother() {
        return mother;
    }


    @Override
    public DatabaseReference queryForHospitals() {
        return databaseReference.child("hospitals");
    }

    @Override
    public ArrayList<Hospital> getHospitals() {
        return hospitals;
    }

    @Override
    public DatabaseReference queryForDoctors(String hospitalId) {
        return databaseReference.child("hospitals").child(hospitalId).child("doctors");
    }

    @Override
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public void createAppointment(Appointment appointment, String userId) {
        String newKey = databaseReference.child("users").child(userId).child("appointments").push().getKey();
        appointment.setKey(newKey);
        databaseReference.child("mothers").child(userId).child("appointments").child(newKey).setValue(appointment);
        databaseReference.child("hospitals").child("appointments").child(appointment.getHospitalId()).child(appointment.getDoctorId()).setValue(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment, String userId) {
        databaseReference.child("mothers").child(userId).child("appointments").child(appointment.getKey()).setValue(appointment);
    }

    @Override
    public DatabaseReference queryForAppointment(String userId) {
        return databaseReference.child("mothers").child(userId).child("appointments");
    }

    @Override
    public ArrayList<Appointment> getAppointments() {

        return appointments;
    }

    private void postEvent(EventType type) {
        EventBus.getDefault().post(new FirebaseEvent(type));
    }
}
