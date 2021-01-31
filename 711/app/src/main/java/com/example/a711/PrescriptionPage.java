package com.example.a711;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PrescriptionPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_page);
        ArrayList<String> info= extras.getStringArrayList("Info");
        Button goBack = (Button) findViewById(R.id.PrescriptionPageGoBack);
        TextView details = (TextView) findViewById(R.id.PrescriptionPageDetails);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrescriptionPage.this, PatientHomePage.class));
            }
        });
        details.setText("Name: "+ info.get(0) +"\nBegin Date: "+info.get(1)+"\nEnd Date: "+info.get(2)+"\nDoctor: "+info.get(3));
    }
}