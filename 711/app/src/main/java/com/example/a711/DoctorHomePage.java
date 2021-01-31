package com.example.a711;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DoctorHomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Map<String, Object> prescriptionMap = new HashMap<>();
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    Spinner patientChoice;
    ArrayAdapter<String> adapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String doctorID = user.getUid();
    Doctor doctor = new Doctor("", "");
    ArrayList<String> patientNames = new ArrayList<>();
    ArrayList<Patient> patients = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG = "DoctorHomePage";
    String doctorName;
    Patient selectedPatient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
        mAuth = FirebaseAuth.getInstance();
        Button submit = (Button) findViewById(R.id.DoctorHomePageSubmit);
        final TextView beginDate = (TextView) findViewById(R.id.DoctorHomePageStartDate);
        final TextView endDate = (TextView) findViewById(R.id.DoctorHomePageEndDate);
        final TextView prescriptionName=(TextView) findViewById(R.id.DoctorHomePagePrescription);
        final String TAG = "DoctorHomePage";
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        TextView dateDisplay = (TextView) findViewById(R.id.DoctorHomePageDate);
        dateDisplay.setText(date);
        final TextView doctorName1=(TextView) findViewById(R.id.DoctorHomePageDoctorName);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, patientNames);
        DocumentReference docRef = db.collection("doctors").document(doctorID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        doctorName = document.getData().values().toArray()[0].toString();
                        doctor.setId(doctorID);
                        doctor.setName(doctorName);
                        Log.d(TAG,doctor.getName());
                    } else {
                        Log.d(TAG, "No such document");
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }


            }
        });
        db.collection("doctors").document(doctorID)
                .collection("Doctor Patients").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Patient patient = new Patient(document.getId(),document.getData().values().toArray()[4].toString(),
                                Integer.parseInt(document.getData().values().toArray()[3].toString()),document.getData().values().toArray()[0].toString());
                        patientNames.add(patient.getName());
                        patients.add(patient);
                        doctorName1.setText(doctor.getName());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


        patientChoice = (Spinner) findViewById(R.id.DoctorHomePagePatientList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patientChoice.setAdapter(adapter);
        patientChoice.setSelection(3);
        patientChoice.setOnItemSelectedListener(this);
        Button logOut = (Button) findViewById(R.id.DoctorHomePageLogoutButton);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(DoctorHomePage.this, LoginPage.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prescription prescription = new Prescription(prescriptionName.getText().toString(), beginDate.getText().toString(), endDate.getText().toString(), doctorName);
                prescriptionMap.put("Name", prescription.getDrug());
                prescriptionMap.put("Begin", prescription.getBeginDate());
                prescriptionMap.put("End", prescription.getEndDate());
                prescriptionMap.put("Doctor", prescription.getDoctor());
                Log.d(TAG,selectedPatient.getId());
                db.collection("patient").document(selectedPatient.getId()).collection("Prescription").document(prescription.getDrug()).set(prescriptionMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DoctorHomePage.this, "Prescription Added", Toast.LENGTH_LONG).show();
                                Log.d(TAG, "Success");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DoctorHomePage.this, "Could Not Add The Prescription!", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Failed");
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String namePatient = patientChoice.getSelectedItem().toString();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getName().equals(namePatient)) {
                selectedPatient = patients.get(i); //Temporary variable containing selected patient info
                Log.d(TAG,selectedPatient.getId());
                break;
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}