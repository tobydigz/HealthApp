package com.digzdigital.healthapp.fragment.antenatal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.mothercare.AntenatalTest;
import com.digzdigital.healthapp.data.model.DateConverter;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Digz on 12/05 /2016. f
 */
public class AntenatalTestsListAdapter extends RecyclerView.Adapter<AntenatalTestsListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    private ArrayList<AntenatalTest> antenatalTests;
    private DateConverter dateConverter = new DateConverter();

    public AntenatalTestsListAdapter(ArrayList<AntenatalTest> antenatalTests) {
        this.antenatalTests = antenatalTests;
    }

    public AntenatalTest getItem(int position) {
        return antenatalTests.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AntenatalTest antenatalTest = getItem(position);

        // holder.drugName.setText(antenatalTest.getDrugName());
        // holder.dosageTime.setText(antenatalTest.getNextDoseTime(today));
        // holder.dosageDate.setText(antenatalTest.getNextDoseDate(today));
        // holder.dosageAmount.setText(antenatalTest.getAmount());
        // holder.drugImage.setImageResource(antenatalTest.getImageResId());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return antenatalTests.size();
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
