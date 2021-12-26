package com.example.pddetectv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link resultfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class resultfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView hyscore,uprsscore,pdgrade;

    public resultfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resultfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static resultfragment newInstance(String param1, String param2) {
        resultfragment fragment = new resultfragment();
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
        ViewGroup resultgroup=(ViewGroup) inflater.inflate(R.layout.fragment_resultfragment, container, false);
        hyscore=(TextView) resultgroup.findViewById(R.id.hyscore);
        uprsscore=(TextView) resultgroup.findViewById(R.id.uprsscore);
        pdgrade=(TextView) resultgroup.findViewById(R.id.pdgrade);

        SharedPreferences profileCheck = getActivity().getSharedPreferences("score", Context.MODE_PRIVATE);
        try {
            Float finalScore = profileCheck.getFloat("finalScore",0);
            Toast.makeText(getActivity(),finalScore.toString(), Toast.LENGTH_SHORT).show();
            uprsscore.setText(finalScore.toString());
            // sample





            hyscore.setText("0.0");
            pdgrade.setText("A");
        }catch (Exception e)
        {
        }



        return resultgroup;

    }
}