package com.digzdigital.healthapp.fragment.appointment.view;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.activity.HomeActivity;
import com.digzdigital.healthapp.data.FirebaseHelper;
import com.digzdigital.healthapp.data.model.appointment.Appointment;
import com.digzdigital.healthapp.databinding.FragmentAppointmentViewBinding;
import com.digzdigital.healthapp.eventbus.EventType;
import com.digzdigital.healthapp.eventbus.FirebaseEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AppointmentViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentViewFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String userId;
    private String mParam2;
private ArrayList<Appointment> appointments;
    private FirebaseHelper firebaseHelper;
    private AppointmentListAdapter appointmentListAdapter;
    private FragmentAppointmentViewBinding binding;
    private HomeActivity home;
    private DatabaseReference reference;

    public AppointmentViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentViewFragment newInstance(String userId, String param2) {
        AppointmentViewFragment fragment = new AppointmentViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userId);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        home = (HomeActivity)getActivity();
        firebaseHelper = home.getFirebaseHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment_view, container, false);

        reference = firebaseHelper.queryForAppointment(userId);
        binding.newAppointment.setOnClickListener(this);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointments = null;
                appointments = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                doRest();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return binding.getRoot();
    }



    private void doRest() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        binding.appointmentRv.setLayoutManager(llm);
        // appointments = firebaseHelper.getAppointments();
        //
        if (appointments == null) return;
        if (appointments.size() > 0) return;
        appointmentListAdapter = new AppointmentListAdapter(appointments);
        binding.appointmentRv.setAdapter(appointmentListAdapter);

        appointmentListAdapter.setOnItemClickListener(new AppointmentListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });
    }


    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFirebaseEvent(FirebaseEvent event){
        if (event.type == EventType.APPOINTMENTS)doRest();
    }

    @Override
    public void onClick(View v) {
        home.onCreateAppointmentClicked();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the home and potentially other fragments contained in that
     * home.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
