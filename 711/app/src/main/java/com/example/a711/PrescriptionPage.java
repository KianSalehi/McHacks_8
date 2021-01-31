package com.example.a711;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrescriptionPage extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_page);
        String name= extras.getString("Name");
        String begin= extras.getString("Begin");
        String end = extras.getString("End");
        String doctor = extras.getString("Doctor");
        Button goBack = (Button) findViewById(R.id.PrescriptionPageGoBack);
        TextView details = (TextView) findViewById(R.id.PrescriptionPageDetails);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrescriptionPage.this, PatientHomePage.class));
            }
        });
        details.setText("Name: "+name+"\nBegin Date: "+begin+"\nEnd Date: "+end+"\nDoctor: "+doctor);
    }
}