package com.digzdigital.healthapp.fragment.immunization;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.activity.HomeActivity;
import com.digzdigital.healthapp.data.FirebaseHelper;
import com.digzdigital.healthapp.data.model.childcare.Immunization;
import com.digzdigital.healthapp.eventbus.EventType;
import com.digzdigital.healthapp.eventbus.FirebaseEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImmunizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImmunizationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    private ImmunizationListAdapter immunizationListAdapter;
    private ArrayList<Immunization> immunizations;
    private FirebaseHelper firebaseHelper;
    private DatabaseReference reference;


    public ImmunizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AntenatalTestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImmunizationFragment newInstance(String param1, String param2) {
        ImmunizationFragment fragment = new ImmunizationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        HomeActivity activity = (HomeActivity)getActivity();
        firebaseHelper = activity.getFirebaseHelper();
        reference = FirebaseDatabase.getInstance().getReference().child("immunizations");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                immunizations = null;
                immunizations = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Immunization immunization = new Immunization();
                    String id = (String)snapshot.child("id").getValue();
                    String name = (String)snapshot.child("name").getValue();
                    long month = (Long)snapshot.child("month").getValue();
                    immunization.setName(name);
                    immunization.setId(id);
                    immunization.setMonth(month);
                    immunizations.add(immunization);
                }
                doRest();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void loadImmunizations() {
        // immunizations = firebaseHelper.getImmunizations();
        // doRest();
    }

    private void doRest() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        if (immunizations == null) return;
        if (immunizations.size() == 0) return;
        immunizationListAdapter = new ImmunizationListAdapter(immunizations);
        rv.setAdapter(immunizationListAdapter);

        immunizationListAdapter.setOnItemClickListener(new ImmunizationListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });


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
        // if (event.type == EventType.IMMUNIZATIONS) loadImmunizations();
    }
}
