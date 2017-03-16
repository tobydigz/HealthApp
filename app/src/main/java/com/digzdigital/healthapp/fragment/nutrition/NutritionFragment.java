package com.digzdigital.healthapp.fragment.nutrition;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.fragment.nutrition.viewpager.MyPagerAdapter;
import com.digzdigital.healthapp.fragment.nutrition.viewpager.ZoomOutPageTransformer;


public class NutritionFragment extends Fragment {

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;

    public NutritionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.scheduleContentFrame);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        pagerAdapter = buildAdapter();
        setupViewPager(viewPager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void setupViewPager(ViewPager viewPager) {
        pagerAdapter.addFragment(FoodTrimesterFragment.newInstance(0, "First Trimester"), "First Trimester");
        pagerAdapter.addFragment(FoodTrimesterFragment.newInstance(1, "Second Trimester"), "Second Trimester");
        pagerAdapter.addFragment(FoodTrimesterFragment.newInstance(2, "Third Trimester"), "Third Trimester");
        viewPager.setAdapter(pagerAdapter);
    }


    private MyPagerAdapter buildAdapter() {
        return (new MyPagerAdapter(getChildFragmentManager()));
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
