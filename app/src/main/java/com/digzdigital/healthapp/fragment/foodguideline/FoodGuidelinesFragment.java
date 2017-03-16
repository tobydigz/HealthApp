package com.digzdigital.healthapp.fragment.foodguideline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodGuidelinesFragment extends Fragment {


    public FoodGuidelinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_guidelines, container, false);
    }

}
