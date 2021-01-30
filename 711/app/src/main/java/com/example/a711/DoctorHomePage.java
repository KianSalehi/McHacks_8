package com.example.a711;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class DoctorHomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Doctor doctor = new Doctor("a","a");
    public ArrayList<Patient> patients = doctor.getPatientList();
    public ArrayList<String> patientNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
        Spinner patientList = findViewById(R.id.DoctorHomePagePatientList);
        for(int i=0; i<patients.size();i++){
            patientNames.add(patients.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,patientNames);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}