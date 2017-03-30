package com.digzdigital.healthapp.fragment.messages;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.Message;

import java.util.ArrayList;


public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    ArrayList<Message> messages;

    public MessagesListAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messages, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Message message = getItem(position);

        holder.messageTitle.setText(message.getMessage());
        holder.messageSender.setText(message.getHospital());
        holder.messageImage.setImageDrawable(createDrawable("Dr. " + message.getDoctor().substring(0, 1)));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    private TextDrawable createDrawable(String name) {
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .roundRect(10);
        return builder.build(name, color1);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView messageImage;
        TextView messageTitle, messageSender;

        ViewHolder(View itemView) {
            super(itemView);
            messageImage = (ImageView) itemView.findViewById(R.id.messageImage);
            messageTitle = (TextView) itemView.findViewById(R.id.messageTitle);
            messageSender = (TextView) itemView.findViewById(R.id.messageSender);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }


    }
}

