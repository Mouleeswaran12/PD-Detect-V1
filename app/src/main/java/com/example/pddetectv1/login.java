package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    EditText userid,password;
    Button homeintentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        homeintentbutton=findViewById(R.id.button);
        userid=findViewById(R.id.userid);
        password=findViewById(R.id.pswd);
        homeintentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeintent=new Intent(login.this,MainActivity.class);
                startActivity(homeintent);
            }
        });
    }
}