package com.example.a711;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DoctorHomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner patientChoice;
    ArrayAdapter<String> adapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String doctorID= user.getUid();
    Doctor doctor = new Doctor("","");
    Patient patient = new Patient("","",0,"");
    ArrayList<String> patientNames = new ArrayList<>();
    ArrayList<Patient> patients = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG ="DoctorHomePage";
    String doctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
        DocumentReference docRef = db.collection("doctors").document(doctorID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        doctorName = document.getData().values().toArray()[0].toString();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
                doctor.setId(doctorID);
                doctor.setName(doctorName);
                db.collection("doctors").document(doctorID).collection("Doctor Patients").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document:task.getResult()) {
                                patient.setId(document.getId());
                                patient.setName(document.getData().values().toArray()[4].toString());
                                patient.setAge(Integer.parseInt(document.getData().values().toArray()[3].toString()));
                                patient.setEmail(document.getData().values().toArray()[0].toString());
                                doctor.addPatient(patient);
                                Log.d("Yas gurl :  ", doctor.getName());
                            }
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
                                                Log.d("doctorName ", doctor.getName());
                                                for (int i = 0; i < doctor.getPatientList().size(); i++) {
                                                    patientNames.add(doctor.getPatientList().get(i).getName());
                                                    Log.d("Successful name: ", patientNames.get(i));
                                                }
                                            } else {
                                                Log.w(TAG, "Error getting documents.", task.getException());
                                            }
                                        }
                                    });
                        }
                    }
                });
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, patientNames);
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