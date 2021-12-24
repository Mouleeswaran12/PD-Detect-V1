package com.example.pddetectv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link surveyfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class surveyfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView question;
    RadioButton opt1,opt2,opt3,opt4;
    ArrayList<RadioButton> options = new ArrayList<RadioButton>();
    Button Prev, nxt,result;
    RadioGroup selected;
    ProgressBar questionProgress;
    int n=0;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Integer[] score;
    int currentProgress=10;

    public surveyfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment surveyfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static surveyfragment newInstance(String param1, String param2) {
        surveyfragment fragment = new surveyfragment();
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

    public String loadJSONFromAsset(){
        String json;
        try {
            InputStream is = getActivity().getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup surveyroot=(ViewGroup) inflater.inflate(R.layout.fragment_surveyfragment, container, false);

        selected =(RadioGroup) surveyroot.findViewById(R.id.answergroup);
        result = (Button) surveyroot.findViewById(R.id.button3);
        questionProgress =(ProgressBar) surveyroot.findViewById(R.id.progressBar);
        questionProgress.setMax(120);
        questionProgress.setProgress(currentProgress);
        result.setVisibility(View.INVISIBLE);
        score = new Integer[12];
        for(int i=0;i<12;i++)
            score[i]=-1;

        question =(TextView) surveyroot.findViewById(R.id.textview4);
        opt1 =(RadioButton) surveyroot.findViewById(R.id.option1);
        opt2 = (RadioButton) surveyroot.findViewById(R.id.option2);
        opt3 = (RadioButton) surveyroot.findViewById(R.id.option3);
        opt4 = (RadioButton) surveyroot.findViewById(R.id.option4);
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);
          Prev = (Button) surveyroot.findViewById(R.id.button);
          nxt = (Button) surveyroot.findViewById(R.id.button2);

        try{
            jsonObject = new JSONObject(loadJSONFromAsset());
            jsonArray = jsonObject.getJSONArray("questions");
        } catch (Exception e) {
            e.printStackTrace();
        }

        questionCall(0);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedid = selected.getCheckedRadioButtonId();

                if(selectedid==-1)
                {
                    Toast.makeText(getActivity(), "Select any of the option", Toast.LENGTH_SHORT).show();
                }
                else {
                    currentProgress = currentProgress+10;
                    questionProgress.setProgress(currentProgress);
                    if (selectedid == opt1.getId())
                        score[n] = 0;
                    else if (selectedid == opt2.getId())
                        score[n] = 1;
                    else if (selectedid == opt3.getId())
                        score[n] = 2;
                    else if (selectedid == opt4.getId())
                        score[n] = 3;

                    n = n + 1;
                    questionCall(n);
                }
            }
        });
        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentProgress = currentProgress-10;
                questionProgress.setProgress(currentProgress);
                n = n-1;
                questionCall(n);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sumScore = 0;
                for (int s : score){
                    sumScore += s;
                }
                SharedPreferences scoreData = getActivity().getSharedPreferences("score", Context.MODE_PRIVATE);
                SharedPreferences.Editor profileEdit = scoreData.edit();
                try{
                    profileEdit.putString("score", score.toString());
                    profileEdit.commit();
                    Toast.makeText(getActivity(), "Survey Submitted. Check the Result Tab!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return surveyroot;
    }
    public void questionCall(int n)
    {
        selected.clearCheck();
        question.setText("");
        opt1.setText("");
        opt2.setText("");
        opt3.setText("");
        opt4.setText("");

        try {
            JSONObject currentObj = jsonArray.getJSONObject(n);
            question.setText(currentObj.getString("question"));
            JSONArray choices = currentObj.getJSONArray("choices");
            for(int i=0; i<4; i++){
                options.get(i).setText(choices.getString(i));
            }
            if (score[n] != -1){
                options.get(score[n]).setChecked(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(n==0)
        {
            Prev.setEnabled(false);
        }
        else
        {
            Prev.setEnabled(true);
        }
        if(n==11)
        {
            nxt.setVisibility(View.INVISIBLE);
            result.setVisibility(View.VISIBLE);
        }
        else
        {
            nxt.setVisibility(View.VISIBLE);
            result.setVisibility(View.INVISIBLE);
        }

    }
}