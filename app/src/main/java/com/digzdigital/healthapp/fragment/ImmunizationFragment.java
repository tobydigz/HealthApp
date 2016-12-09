package com.digzdigital.healthapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.adapter.ImmunizationListAdapter;
import com.digzdigital.healthapp.adapter.PrescriptionListAdapter;
import com.digzdigital.healthapp.model.data.Immunization;
import com.digzdigital.healthapp.model.data.Prescription;

import java.util.Date;

import io.realm.RealmResults;


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


    public ImmunizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrescriptionFragment.
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
        return view;
    }

    private void doRest() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        RealmResults<Immunization> immunizations = getImmunizations();

        if (immunizations == null) return;
        if (immunizations.size() > 0) return;
        immunizationListAdapter = new ImmunizationListAdapter(immunizations);
        rv.setAdapter(immunizationListAdapter);

        immunizationListAdapter.setOnItemClickListener(new ImmunizationListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });


    }

    private RealmResults<Immunization> getImmunizations() {
        return null;
    }

}