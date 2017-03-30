package com.digzdigital.healthapp.fragment.messages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagesFragment extends Fragment implements ValueEventListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String userId;
    private ArrayList<Message> messages;
    private RecyclerView messageRv;
    private DatabaseReference reference;

    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId Parameter 1.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String userId) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        messageRv = (RecyclerView)view.findViewById(R.id.messageRv);
        reference = FirebaseDatabase.getInstance().getReference().child(userId).child("messages");
        reference.addListenerForSingleValueEvent(this);
        return view;
    }




    protected void doRest() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        messageRv.setLayoutManager(linearLayoutManager);
        if (messages != null) {
            if (messages.size() > 0) {
                MessagesListAdapter messagesListAdapter = new MessagesListAdapter(messages);
                messageRv.setAdapter(messagesListAdapter);

                messagesListAdapter.setOnItemClickListener(new MessagesListAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
//                        Handle click whatever
                    }
                });
            }
        }

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        messages = new ArrayList<>();
        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
            Message message = snapshot.getValue(Message.class);
            messages.add(message);
        }
        doRest();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
