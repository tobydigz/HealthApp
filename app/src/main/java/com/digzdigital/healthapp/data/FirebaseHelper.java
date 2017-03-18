package com.digzdigital.healthapp.data;

import com.digzdigital.healthapp.data.model.Child;
import com.digzdigital.healthapp.data.model.Mother;
import com.digzdigital.healthapp.data.model.appointment.Appointment;
import com.digzdigital.healthapp.data.model.appointment.Doctor;
import com.digzdigital.healthapp.data.model.appointment.Hospital;
import com.digzdigital.healthapp.data.model.childcare.Immunization;
import com.digzdigital.healthapp.data.model.mothercare.AntenatalTest;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Digz on 09/03/2017.
 */

public interface FirebaseHelper {
    DatabaseReference queryForAntenatalTests();
    ArrayList<AntenatalTest> getAntenatalTests();

    DatabaseReference queryForImmunizations();
    ArrayList<Immunization> getImmunizations();

    DatabaseReference queryForMother(String userId);
    Mother getMother();


    DatabaseReference queryForHospitals();
    ArrayList<Hospital> getHospitals();

    DatabaseReference queryForDoctors(String hospitalId);
    ArrayList<Doctor> getDoctors();

    void createAppointment(Appointment appointment, String userId);
    void updateAppointment(Appointment appointment, String userId);
    DatabaseReference queryForAppointment(String userId);
    ArrayList<Appointment> getAppointments();
}
