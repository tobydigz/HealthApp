package com.digzdigital.healthapp.fragment.appointment.create_edit;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.activity.HomeActivity;
import com.digzdigital.healthapp.data.FirebaseHelper;
import com.digzdigital.healthapp.data.model.appointment.Appointment;
import com.digzdigital.healthapp.data.model.appointment.Doctor;
import com.digzdigital.healthapp.data.model.appointment.Hospital;
import com.digzdigital.healthapp.databinding.FragmentCreateAppointmentBinding;
import com.digzdigital.healthapp.eventbus.EventType;
import com.digzdigital.healthapp.eventbus.FirebaseEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAppointmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateAppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAppointmentFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Appointment appointment = new Appointment();
    private FragmentCreateAppointmentBinding binding;
    private boolean isNew;
    private FirebaseHelper firebaseHelper;
    private ArrayList<Hospital> hospitals;
    private ArrayList<Doctor> doctors;
    private Date date;
    private DatabaseReference databaseReference;
    private Calendar calendar = new GregorianCalendar();

    private OnFragmentInteractionListener listener;
    private HomeActivity home;

    public CreateAppointmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param appointment Parameter 1.
     * @param isNew       Parameter 2.
     * @return A new instance of fragment CreateAppointmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAppointmentFragment newInstance(Appointment appointment, boolean isNew) {
        CreateAppointmentFragment fragment = new CreateAppointmentFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, appointment);
        args.putBoolean(ARG_PARAM2, isNew);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appointment = getArguments().getParcelable(ARG_PARAM1);
            isNew = getArguments().getBoolean(ARG_PARAM2);
        }
        home = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_appointment, container, false);
        binding.save.setOnClickListener(this);
        binding.cancel.setOnClickListener(this);
        binding.appointmentTime.setOnClickListener(this);
        binding.appointmentDate.setOnClickListener(this);
        firebaseHelper = home.getFirebaseHelper();
        if (!isNew) {
            updateViews();
        }
        if (isNew) {
            databaseReference = firebaseHelper.queryForHospitals();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    hospitals = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Hospital hospital = snapshot.getValue(Hospital.class);
                        hospitals.add(hospital);
                    }
                    loadHospitals();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return binding.getRoot();
    }

    private void updateViews() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                listener.onButtonClicked();
                break;
            case R.id.cancel:
                listener.onButtonClicked();
                break;
            case R.id.appointmentDate:
                setDate("");
                break;
            case R.id.appointmentTime:
                setTime("Set appointment time");
                break;
        }
    }

    private void setTime(String title) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog dialog = TimePickerDialog.newInstance(this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true);
        dialog.setTitle(title);
        dialog.show(getFragmentManager(), "Da");
    }

    private void setDate(String title) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dialog = DatePickerDialog.newInstance(this,
                now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.MONTH),
                now.get(Calendar.YEAR));
        dialog.show(getFragmentManager(), "Da");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFirebaseEvent(FirebaseEvent event) {
        // if (event.type == EventType.HOSPITALS)
        // if (event.type == EventType.DOCTORS) loadDoctors();
    }

    private void loadDoctors() {
        // doctors = firebaseHelper.getDoctors();
        String[] ITEMS  = new String[doctors.size()];
        for (int i = 0; i < doctors.size(); i++) {
            ITEMS[i] = doctors.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.doctorSelect.setAdapter(adapter);
        binding.doctorSelect.setOnItemSelectedListener(this);
    }

    private void loadHospitals() {
        // hospitals = firebaseHelper.getHospitals();
        String[] ITEMS  = new String[hospitals.size()];
        for (int i = 0; i < hospitals.size(); i++) {
            ITEMS[i] = hospitals.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.hospitalSelect.setAdapter(adapter);
        binding.hospitalSelect.setOnItemSelectedListener(this);

    }

    private void onHospitalSelected(int position) {
        binding.doctorSelect.setVisibility(View.VISIBLE);
        Hospital hospital = hospitals.get(position);
        appointment.setHospitalName(hospital.getName());
        appointment.setHospitalAddress(hospital.getAddress());
        appointment.setHospitalId(hospital.getId());

        databaseReference = firebaseHelper.queryForDoctors(hospital.getId());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctors = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = snapshot.getValue(Doctor.class);
                    doctors.add(doctor);
                }
                loadDoctors();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void onDoctorSelected(int position) {
        Doctor doctor = doctors.get(position);
        appointment.setDoctorId(doctor.getId());
        appointment.setDoctorName(doctor.getName());
        binding.appointmentTime.setVisibility(View.VISIBLE);
    }

    private void onTimeSelected() {
        binding.appointmentDate.setVisibility(View.VISIBLE);
    }

    private void onDateSelected() {
        appointment.setDate(date);
        loadPatientName();
    }

    private void loadPatientName() {
        String name = binding.patientName.getText().toString().trim();
        if (name.isEmpty()) {
            binding.patientName.setError("Input a patient name");
            return;
        }
        binding.patientName.setError(null);
        appointment.setPatientName(name);
        firebaseHelper.createAppointment(appointment, appointment.getMotherId());
    }

    private void createTime(int hourOfDay, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

    }

    private void createDate(int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        createDate(year, monthOfYear, dayOfMonth);
        binding.appointmentDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
        onDateSelected();
        appointment.setDate(calendar.getTime());

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        createTime(hourOfDay, minute);
        binding.appointmentTime.setText(hourOfDay + ":" + minute);
        onTimeSelected();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
switch (view.getId()){
    case R.id.hospitalSelect:
        onHospitalSelected(position);
        break;
    case R.id.doctorSelect:
        onDoctorSelected(position);
        break;
}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onButtonClicked();

    }
}
