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
    public void queryForAntenatalTests() {
        databaseReference.child("tests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                antenatalTests = null;
                antenatalTests = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AntenatalTest antenatalTest = snapshot.getValue(AntenatalTest.class);
                    antenatalTests.add(antenatalTest);
                }
                postEvent(EventType.TESTS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<AntenatalTest> getAntenatalTests() {
        return antenatalTests;
    }

    @Override
    public void queryForImmunizations() {
        databaseReference.child("immunizations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                immunizations = null;
                immunizations = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Immunization immunization = snapshot.getValue(Immunization.class);
                    immunizations.add(immunization);
                }
                postEvent(EventType.IMMUNIZATIONS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Immunization> getImmunizations() {
        return immunizations;
    }

    @Override
    public void queryForMother(String id) {
        databaseReference.child("mothers").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mother = dataSnapshot.getValue(Mother.class);
                postEvent(EventType.MOTHER);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public Mother getMother() {
        return mother;
    }

    @Override
    public void queryForChildren(String id) {
        databaseReference.child("mothers").child(id).child("children").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                children = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Child child = snapshot.getValue(Child.class);
                    children.add(child);
                }
                postEvent(EventType.CHILDREN);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Child> getChildren() {
        return children;
    }

    @Override
    public void queryForHospitals() {
        databaseReference.child("hospitals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hospitals = null;
                hospitals = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Hospital hospital = snapshot.getValue(Hospital.class);
                    hospitals.add(hospital);
                }
                postEvent(EventType.HOSPITALS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Hospital> getHospitals() {
        return hospitals;
    }

    @Override
    public void queryForDoctors(String hospitalId) {
        databaseReference.child("hospitals").child(hospitalId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctors = null;
                doctors = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = snapshot.getValue(Doctor.class);
                    doctors.add(doctor);
                }
                postEvent(EventType.DOCTORS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public void createAppointment(Appointment appointment, String userId) {
        String newKey = databaseReference.child("users").child(userId).child("appointments").push().getKey();
        appointment.setKey(newKey);
        databaseReference.child("users").child(userId).child("appointments").child(newKey).setValue(appointment);
        databaseReference.child("hospitals").child(appointment.getHospitalId()).child(appointment.getDoctorId()).setValue(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment, String userId) {
        databaseReference.child("users").child(userId).child("appointments").child(appointment.getKey()).setValue(appointment);
    }

    @Override
    public void queryForAppointment(String userId) {
        databaseReference.child(userId).child("appointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointments = null;
                appointments = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                postEvent(EventType.APPOINTMENTS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Appointment> getAppointments() {

        return appointments;
    }

    private void postEvent(EventType type) {
        EventBus.getDefault().post(new FirebaseEvent(type));
    }
}
