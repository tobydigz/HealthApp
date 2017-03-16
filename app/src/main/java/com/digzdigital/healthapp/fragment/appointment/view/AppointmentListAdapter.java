package com.digzdigital.healthapp.fragment.appointment.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.DateConverter;
import com.digzdigital.healthapp.data.model.appointment.Appointment;

import java.util.ArrayList;


public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    private ArrayList<Appointment> appointments;
    private DateConverter dateConverter = new DateConverter();

    public AppointmentListAdapter(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Appointment getItem(int position) {
        return appointments.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Appointment appointment = getItem(position);
        holder.doctorName.setText(appointment.getDoctorName());
        holder.hospitalName.setText(appointment.getHospitalName());
        String date = dateConverter.getFullDate(appointment.getDate());
        holder.appointmentDate.setText(date);
        holder.appointmentStatus.setText(appointment.getStatus());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hospitalName, appointmentDate, doctorName, appointmentStatus;
        ImageView drugImage;

        ViewHolder(View itemView) {
            super(itemView);
            hospitalName = (TextView) itemView.findViewById(R.id.hospitalName);
            doctorName = (TextView) itemView.findViewById(R.id.doctorName);
            appointmentDate = (TextView) itemView.findViewById(R.id.appointmentDate);
            appointmentStatus = (TextView) itemView.findViewById(R.id.appointmentStatus);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
