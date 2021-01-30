package com.example.a711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DoctorHomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Doctor doctor = new Doctor("a","a");
    ArrayList<Patient> patients = doctor.getPatientList();
    ArrayList<String> patientNames = new ArrayList<>();
    Spinner patientChoice;
    ArrayAdapter<String> adapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String doctorID= user.getUid();
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG ="DoctorHomePage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        for (int i = 0; i < patients.size(); i++) {
            patientNames.add(patients.get(i).getName());
        }
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, patientNames);
        mAuth = FirebaseAuth.getInstance();
        patientChoice = (Spinner) findViewById(R.id.DoctorHomePagePatientList);
        db.collection("doctors").document(doctorID).collection("Doctor Patients").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patientChoice.setAdapter(adapter);
        patientChoice.setSelection(1);
        patientChoice.setOnItemSelectedListener(this);
        Button logOut= (Button) findViewById(R.id.DoctorHomePageLogoutButton);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(DoctorHomePage.this, LoginPage.class));
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