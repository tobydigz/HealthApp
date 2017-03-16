package com.digzdigital.healthapp.fragment.mother;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.activity.HomeActivity;
import com.digzdigital.healthapp.data.FirebaseHelper;
import com.digzdigital.healthapp.data.model.Mother;
import com.digzdigital.healthapp.databinding.FragmentMotherBinding;
import com.digzdigital.healthapp.eventbus.EventType;
import com.digzdigital.healthapp.eventbus.FirebaseEvent;
import com.digzdigital.healthapp.fragment.immunization.ImmunizationListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MotherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MotherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MotherFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String userId;
    private String mParam2;

    private FragmentMotherBinding binding;
    private FirebaseHelper firebaseHelper;
    private Mother mother = new Mother();
    private OnFragmentInteractionListener listener;
    private ChildListAdapter childListAdapter;

    public MotherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MotherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MotherFragment newInstance(String userId, String param2) {
        MotherFragment fragment = new MotherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userId);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        HomeActivity activity = (HomeActivity)getActivity();
        firebaseHelper = activity.getFirebaseHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mother, container, false);
        updateUI();
        firebaseHelper.queryForMother(userId);
        return binding.getRoot();
    }

    private void updateUI() {
        binding.motherName.setText(mother.getName());
        binding.motherAge.setText(mother.getAge());
        binding.motherBloodGroup.setText(mother.getBloodGroup());
        binding.motherBloodType.setText(mother.getBloodType());
        binding.motherDOB.setText(mother.getDateOfBirth());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFirebaseEvent(FirebaseEvent event){
        if (event.type == EventType.MOTHER)loadMother();
    }

    private void loadMother() {
        mother = firebaseHelper.getMother();
        updateUI();
        doRest();
    }

    private void doRest() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        binding.childrenRv.setLayoutManager(llm);

        if (mother.getChildren() == null) return;
        if (mother.getChildren().size() > 0) return;
        childListAdapter = new ChildListAdapter(mother.getChildren());
        binding.childrenRv.setAdapter(childListAdapter);

        childListAdapter.setOnItemClickListener(new ChildListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });


    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
    }
}
