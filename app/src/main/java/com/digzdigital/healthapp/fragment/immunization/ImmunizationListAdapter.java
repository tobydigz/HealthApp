package com.digzdigital.healthapp.fragment.immunization;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.childcare.Immunization;

import java.util.ArrayList;


/**
 * Created by Digz on 12/05 /2016. f
 */
public class ImmunizationListAdapter extends RecyclerView.Adapter<ImmunizationListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    private ArrayList<Immunization> immunizations;

    public ImmunizationListAdapter(ArrayList<Immunization> immunizations) {
        this.immunizations = immunizations;
    }

    public Immunization getItem(int position) {
        return immunizations.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.immunization_card, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Immunization immunization = getItem(position);

        holder.immunizationName.setText(immunization.getName());
        holder.immunizationDate.setText(immunization.getMonth() + "(s) after birth" );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return immunizations.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView immunizationName, immunizationDate, immunizationTime, immunizationLocation;
        ImageView drugImage;
        ViewHolder(View itemView) {
            super(itemView);
            immunizationName = (TextView) itemView.findViewById(R.id.immunizationName);
            // immunizationTime = (TextView) itemView.findViewById(R.id.immunizationTime);
            immunizationDate = (TextView) itemView.findViewById(R.id.immunizationDate);
            // immunizationLocation = (TextView) itemView.findViewById(R.id.immunizationLocation);
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
