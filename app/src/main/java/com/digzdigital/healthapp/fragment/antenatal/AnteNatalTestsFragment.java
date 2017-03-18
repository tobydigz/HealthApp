package com.digzdigital.healthapp.fragment.antenatal;


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
import com.digzdigital.healthapp.data.model.mothercare.AntenatalTest;
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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AntenatalTestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AntenatalTestsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    private AntenatalTestsListAdapter antenatalTestsListAdapter;
    private ArrayList<AntenatalTest> antenatalTests;
    private FirebaseHelper firebaseHelper;
    private DatabaseReference databaseReference;

    public AntenatalTestsFragment() {
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
    public static AntenatalTestsFragment newInstance(String param1, String param2) {
        AntenatalTestsFragment fragment = new AntenatalTestsFragment();
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
        HomeActivity activity = (HomeActivity)getActivity();
        firebaseHelper = activity.getFirebaseHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                antenatalTests = null;
                antenatalTests = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AntenatalTest antenatalTest = new AntenatalTest();
                    String name = (String)snapshot.child("name").getValue();
                    String id = (String)snapshot.child("id").getValue();
                    Long trimester = (Long) snapshot.child("trimester").getValue();
                    antenatalTest.setName(name);
                    antenatalTest.setId(id);
                    antenatalTest.setTrimester(trimester);

                    antenatalTests.add(antenatalTest);
                }
                doRest();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void doRest() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        // antenatalTests = firebaseHelper.getAntenatalTests();
        //
        if (antenatalTests == null) return;
        if (antenatalTests.size() > 0) return;
        antenatalTestsListAdapter = new AntenatalTestsListAdapter(antenatalTests);
        rv.setAdapter(antenatalTestsListAdapter);

        antenatalTestsListAdapter.setOnItemClickListener(new AntenatalTestsListAdapter.MyClickListener() {
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
        // if (event.type == EventType.TESTS)doRest();
    }
}