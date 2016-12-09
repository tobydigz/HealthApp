package com.digzdigital.healthapp.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.adapter.NutritionListAdapter;
import com.digzdigital.healthapp.adapter.PrescriptionListAdapter;
import com.digzdigital.healthapp.databinding.FragmentNutritionBinding;
import com.digzdigital.healthapp.model.data.Food;

import java.util.Date;

import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NutritionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NutritionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentNutritionBinding binding;
    private NutritionListAdapter nutritionListAdapter;
    private int trimester = 0, day = 0;

    public NutritionFragment() {
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
    public static NutritionFragment newInstance(String param1, String param2) {
        NutritionFragment fragment = new NutritionFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nutrition, container, false);
        binding.nutritionTrimester.setOnItemSelectedListener(new ItemSelectedListener());
        binding.nutritionDay.setOnItemSelectedListener(new ItemSelectedListener());
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        binding.nutritionRV.setLayoutManager(llm);
        return binding.getRoot();
    }
    private void doRest() {

        RealmResults<Food> foods = getFood(trimester, day);

        if (foods == null) return;
        if (foods.size() > 0) return;
        nutritionListAdapter = new NutritionListAdapter(foods);
        binding.nutritionRV.setAdapter(nutritionListAdapter);

        nutritionListAdapter.setOnItemClickListener(new NutritionListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });


    }


    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            Log.d("DIGZ:DEVICE", "Item Selected");
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {
                case R.id.nutritionTrimester:
                    trimester = position;
                    Log.d("DIGZ:DEVICE", "trimester: " + position);
                    break;
                case R.id.nutritionDay:
                    day = position;
                    Log.d("DIGZ:DEVICE", "day: " + position);
                    break;
            }
            doRest();
        }


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private RealmResults<Food> getFood(int trimester, int day) {
        return null;
    }
}
