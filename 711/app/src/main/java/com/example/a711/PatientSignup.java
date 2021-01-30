package com.example.a711;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PatientSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);
        Button signUp= (Button) findViewById(R.id.PatientSignupButton);
        TextView email = (TextView) findViewById(R.id.PatientSignupEmail);
        TextView password = (TextView) findViewById(R.id.PatientSignupPass);
    }
}