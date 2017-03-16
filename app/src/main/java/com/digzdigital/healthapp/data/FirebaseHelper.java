package com.digzdigital.healthapp.data;

import com.digzdigital.healthapp.data.model.Child;
import com.digzdigital.healthapp.data.model.Mother;
import com.digzdigital.healthapp.data.model.appointment.Appointment;
import com.digzdigital.healthapp.data.model.appointment.Doctor;
import com.digzdigital.healthapp.data.model.appointment.Hospital;
import com.digzdigital.healthapp.data.model.childcare.Immunization;
import com.digzdigital.healthapp.data.model.mothercare.AntenatalTest;

import java.util.ArrayList;

/**
 * Created by Digz on 09/03/2017.
 */

public interface FirebaseHelper {
    void queryForAntenatalTests();
    ArrayList<AntenatalTest> getAntenatalTests();

    void queryForImmunizations();
    ArrayList<Immunization> getImmunizations();

    void queryForMother(String userId);
    Mother getMother();

    void queryForChildren(String userId);
    ArrayList<Child> getChildren();

    void queryForHospitals();
    ArrayList<Hospital> getHospitals();

    void queryForDoctors(String hospitalId);
    ArrayList<Doctor> getDoctors();

    void createAppointment(Appointment appointment, String userId);
    void updateAppointment(Appointment appointment, String userId);
    void queryForAppointment(String userId);
    ArrayList<Appointment> getAppointments();
}
