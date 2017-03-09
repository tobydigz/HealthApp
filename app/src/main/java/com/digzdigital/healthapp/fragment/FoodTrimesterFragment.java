package com.digzdigital.healthapp.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.digzdigital.healthapp.HomeActivity;
import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.adapter.NutritionListAdapter;
import com.digzdigital.healthapp.databinding.FragmentFoodTrimesterBinding;
import com.digzdigital.healthapp.model.data.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodTrimesterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodTrimesterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "trimester";
    private static final String ARG_PARAM2 = "type";

    // TODO: Rename and change types of parameters
    private int trimesterNum;
    private String trimester;
    private NutritionListAdapter nutritionListAdapter;
    private int day = 0;
    private String jsonString;
    private HomeActivity home;
    private RecyclerView recyclerView;
    private Spinner daySelect;
    private TextView trimesterText;

    public FoodTrimesterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param filePath  Parameter 1.
     * @param trimester Parameter 2.
     * @return A new instance of fragment FoodTrimesterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodTrimesterFragment newInstance(int filePath, String trimester) {
        FoodTrimesterFragment fragment = new FoodTrimesterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, filePath);
        args.putString(ARG_PARAM2, trimester);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            trimesterNum = getArguments().getInt(ARG_PARAM1);
            trimester = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_trimester, container, false);
        // Inflate the layout for this fragment
        daySelect = (Spinner)view.findViewById(R.id.nutritionDay);
        trimesterText = (TextView)view.findViewById(R.id.foodTrimester);
        daySelect.setOnItemSelectedListener(new ItemSelectedListener());
        trimesterText.setText(trimester);
        recyclerView = (RecyclerView) view.findViewById(R.id.nutritionRV);


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        home = (HomeActivity)getActivity();
        jsonString = home.readAssetsFile();
        doRest();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void doRest() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        ArrayList<Food> foods = getFood(day, trimesterNum);

        if (foods == null) return;
        if (foods.size() == 0) return;
        nutritionListAdapter = new NutritionListAdapter(foods);
        recyclerView.setAdapter(nutritionListAdapter);

        nutritionListAdapter.setOnItemClickListener(new NutritionListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });
    }


    private ArrayList<Food> getFood(int day, int trimester) {
        // Log.d("DIGZ:HEBRON", "I got here");
        ArrayList<Food> foods = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray trimesters = jsonObject.getJSONArray("trimester");
            JSONObject trimesterObject = trimesters.getJSONObject(trimester);
            JSONArray days = trimesterObject.getJSONArray("day");

            JSONObject dayObject = days.getJSONObject(day);
            String[] types = {"breakfast", "snack_a", "lunch", "snack_b", "dinner"};
            for (int type = 0; type < types.length; type++) {
                String description = dayObject.getString(types[type]);
                Food food = new Food(description, type, day);
                foods.add(food);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return foods;
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            Log.d("DIGZ:DEVICE", "Item Selected");
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {
                case R.id.nutritionDay:
                    day = position;
                    doRest();
                    break;
            }
            doRest();
        }


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
