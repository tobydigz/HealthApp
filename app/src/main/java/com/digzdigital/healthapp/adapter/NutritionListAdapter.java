package com.digzdigital.healthapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.model.data.Food;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmResults;

/**
 * Created by Digz on 12/05 /2016. f
 */
public class NutritionListAdapter extends RecyclerView.Adapter<NutritionListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    private ArrayList<Food> foods;

    public NutritionListAdapter(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public Food getItem(int position) {
        return foods.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nutrition_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Food food = getItem(position);

        holder.foodType.setText(food.getType());
        holder.foodDescription.setText(food.getDescription());
        holder.foodImage.setImageResource(food.getImageResId());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView foodType, foodDescription;
        ImageView foodImage;
        ViewHolder(View itemView) {
            super(itemView);
            foodType = (TextView) itemView.findViewById(R.id.foodType);
            foodDescription = (TextView) itemView.findViewById(R.id.foodDescription);
            foodImage = (ImageView) itemView.findViewById(R.id.foodImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }


    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
}
