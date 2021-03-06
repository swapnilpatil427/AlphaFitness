package com.example.swapn.alphafitness.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.swapn.alphafitness.R;
import com.example.swapn.alphafitness.RecordWorkOutActivity;
import com.example.swapn.alphafitness.common.Util;
import com.example.swapn.alphafitness.models.UserModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditProfileDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditProfileDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private UserModel user;

    EditText input_weight;
    EditText input_height;
    EditText input_name;
    Button update;

    Util u;


    private String gender = "";
    Double inputHeight;
    Double inputWeight;
    private String name = "";

    private OnFragmentInteractionListener mListener;

    public EditProfileDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileDetailsFragment newInstance(String param1, String param2) {
        EditProfileDetailsFragment fragment = new EditProfileDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_details, container, false);
        input_height = (EditText) view.findViewById(R.id.input_height);
        input_weight = (EditText) view.findViewById(R.id.input_weight);
        input_name = (EditText) view.findViewById(R.id.input_name);
        update = (Button) view.findViewById(R.id.btn_update);
        user = ((RecordWorkOutActivity) getActivity()).getUserData();
        input_height.setText(user.getHeight().toString());
        input_weight.setText(user.getWeight().toString());
        input_name.setText(user.getName());
        u = new Util();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    inputHeight = Double.parseDouble(input_height.getText().toString());
                    inputWeight = Double.parseDouble(input_weight.getText().toString());
                    user.setName(input_name.getText().toString());
                    user.setHeight(inputHeight.toString());
                    user.setWeight(inputWeight.toString());
                    u.setSharedPreferences(Util.getContext(), user);
                    ((RecordWorkOutActivity) getActivity()).openProfileDetailFragment();
                } catch(Exception e) {
                    Toast.makeText(Util.getContext(), "check height and weight values", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void openProfileDetailFragment();
    }
}
