package com.digzdigital.healthapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.model.DateConverter;
import com.digzdigital.healthapp.model.data.Prescription;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmResults;

/**
 * Created by Digz on 12/05 /2016. f
 */
public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    private RealmResults<Prescription> prescriptions;
    private Context context;
    private DateConverter dateConverter = new DateConverter();
    private Date today;

    public PrescriptionListAdapter(RealmResults<Prescription> prescriptions, Context context, Date today) {
        this.prescriptions = prescriptions;
        this.context = context;
        this.today = today;
    }

    public Prescription getItem(int position) {
        return prescriptions.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Prescription prescription = getItem(position);

        holder.drugName.setText(prescription.getDrugName());
        holder.dosageTime.setText(prescription.getNextDoseTime(today));
        holder.dosageDate.setText(prescription.getNextDoseDate(today));
        holder.dosageAmount.setText(prescription.getAmount());
        holder.drugImage.setImageResource(prescription.getImageResId());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return prescriptions.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView drugName, dosageDate, dosageTime, dosageAmount;
        ImageView drugImage;
        ViewHolder(View itemView) {
            super(itemView);
            drugName = (TextView) itemView.findViewById(R.id.drugName);
            dosageTime = (TextView) itemView.findViewById(R.id.dosageTime);
            dosageDate = (TextView) itemView.findViewById(R.id.dosageDate);
            dosageAmount = (TextView) itemView.findViewById(R.id.dosageAmount);
            drugImage = (ImageView) itemView.findViewById(R.id.drugImage);
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
