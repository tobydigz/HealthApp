package com.digzdigital.healthapp.fragment.mother;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.Child;
import com.digzdigital.healthapp.data.model.childcare.Immunization;

import java.util.ArrayList;


public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    private ArrayList<Child> children;

    public ChildListAdapter(ArrayList<Child> children) {
        this.children = children;
    }

    public Child getItem(int position) {
        return children.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.immunization_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Child child = getItem(position);

        holder.childName.setText(child.getName());
        holder.childAge.setText(child.getAge());
        holder.childDOB.setText(child.getDateOfBirth());
        holder.childBloodType.setText(child.getBloodType());
        holder.childBloodGroup.setText(child.getBloodGroup());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return children.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView childName, childAge, childDOB, childBloodGroup, childBloodType;
        ViewHolder(View itemView) {
            super(itemView);
            childName = (TextView) itemView.findViewById(R.id.childName);
            childDOB = (TextView) itemView.findViewById(R.id.childDOB);
            childAge = (TextView) itemView.findViewById(R.id.childAge);
            childBloodGroup = (TextView) itemView.findViewById(R.id.childBloodGroup);
            childBloodType = (TextView) itemView.findViewById(R.id.childBloodType);
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
