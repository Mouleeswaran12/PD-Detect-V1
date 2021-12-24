package com.example.pddetectv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profilefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profilefragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profilefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profilefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profilefragment newInstance(String param1, String param2) {
        profilefragment fragment = new profilefragment();
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

        ViewGroup rootprofile=(ViewGroup) inflater.inflate(R.layout.fragment_profilefragment, container, false);
        return rootprofile;
        /*
        Button submit =(Button) rootprofile.findViewById(R.id.submit);
        TextView name =(TextView) rootprofile.findViewById(R.id.name);
        TextView age =(TextView) rootprofile.findViewById(R.id.age);
        TextView gender =(TextView) rootprofile.findViewById(R.id.spinnertext);
        TextView height =(TextView) rootprofile.findViewById(R.id.height);
        TextView weight =(TextView) rootprofile.findViewById(R.id.weight);
        TextView passwrd=(TextView) rootprofile.findViewById(R.id.password);

        final AutoCompleteTextView genderAuto = (AutoCompleteTextView) rootprofile.findViewById(R.id.spinnertext);

        ArrayList<String> genderList = getgender();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(profilefragment.this, android.R.layout.simple_spinner_item, genderList);
        fillDetails();
        genderAuto.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences profileData = getSharedPreferences("profile",MODE_PRIVATE);
                SharedPreferences.Editor profileEdit = profileData.edit();
                try{
                    profileEdit.putString("name", name.getText().toString());
                    profileEdit.putInt("age",Integer.parseInt(age.getText().toString()));
                    profileEdit.putString("gender", gender.getText().toString());
                    profileEdit.putFloat("height",Float.parseFloat(height.getText().toString()));
                    profileEdit.putFloat("weight",Float.parseFloat(weight.getText().toString()));
                    profileEdit.putString("password",passwrd.getText().toString());
                    profileEdit.commit();
                    Toast.makeText(profilefragment.this, "Profile Updated Successfilly", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Fill all the details!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
                return rootprofile;
            }
        });
    }
    private ArrayList<String> getgender()
    {
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        return gender;
    }

    private void fillDetails(){
        SharedPreferences profileCheck = getSharedPreferences("profile", MODE_PRIVATE);
        String nameValue = profileCheck.getString("name","");
        if(!nameValue.equals("")){
            // Home button enable & visible
            submit.setText("UPDATE PROFILE");

            Integer ageValue = profileCheck.getInt("age", 0);
            String genderValue = profileCheck.getString("gender","");
            Float heightValue = profileCheck.getFloat("height",0);
            Float weightValue = profileCheck.getFloat("weight",0);
            String passwordretrival=profileCheck.getString("password","");

            name.setText(nameValue);
            age.setText(ageValue.toString());
            gender.setText(genderValue);
            height.setText(heightValue.toString());
            weight.setText(weightValue.toString());
            passwrd.setText(passwordretrival);

        }*/

    }
}