package com.example.a711;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DoctorHomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Doctor doctor = new Doctor("a","a");
    public ArrayList<Patient> patients = doctor.getPatientList();
    public ArrayList<String> patientNames = new ArrayList<>();
    public Spinner patientChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
        Spinner patientList = findViewById(R.id.DoctorHomePagePatientList);
        for (int i = 0; i < patients.size(); i++) {
            patientNames.add(patients.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, patientNames);

        patientChoice = (Spinner) findViewById(R.id.DoctorHomePagePatientList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patientChoice.setAdapter(adapter);
        patientChoice.setSelection(1);
        patientChoice.setOnItemSelectedListener(this);
        final Button done = findViewById(R.id.ChooseADoctorAfterSignupDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Done:","Submit");
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String namePatient= patientChoice.getSelectedItem().toString();
        Patient selectedPatient = null;
        for(int i=0;i<patients.size();i++){
            if (patients.get(i).getName().equals(namePatient)){
                selectedPatient = patients.get(i); //Temporary variable containing selected patient info
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}