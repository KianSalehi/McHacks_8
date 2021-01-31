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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PatientHomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Map<String, Object> userMap = new HashMap<>();
    ArrayList<String> spinnerValueHold = new ArrayList<>();
    ArrayList<Prescription> prescriptionList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner patientPrescription;
    Prescription prescriptionDetails = null;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG ="PatientHomePage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerValueHold);
        db.collection("patient").document(user.getUid()).collection("Prescription")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document: task.getResult()){
                        Prescription prescription = new Prescription(document.getId(),
                                document.getData().values().toArray()[1].toString(),document.getData().values().toArray()[2].toString(),
                                document.getData().values().toArray()[3].toString());
                        prescriptionList.add(prescription);
                        spinnerValueHold.add(prescription.getDrug());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        patientPrescription = (Spinner) findViewById(R.id.PatientHomePagePrescriptionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patientPrescription.setAdapter(adapter);
        patientPrescription.setSelection(0);
        patientPrescription.setOnItemSelectedListener(this);
        mAuth=FirebaseAuth.getInstance();
        TextView patientName = (TextView) findViewById(R.id.PatientHomePagePatientName);
        patientName.setText(user.getDisplayName());
        TextView dateDisplay = (TextView) findViewById(R.id.PatientHomePageDate);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateDisplay.setText(date);
        Button logOut = (Button) findViewById(R.id.PatientHomePageLogout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PatientHomePage.this, LoginPage.class));
            }
        });
        Button prescriptionDetailsButton = (Button) findViewById(R.id.PatientHomePageDetails);
        prescriptionDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomePage.this,PrescriptionPage.class);
                intent.putExtra("Name", prescriptionDetails.getDrug());
                intent.putExtra("Begin", prescriptionDetails.getBeginDate());
                intent.putExtra("End", prescriptionDetails.getEndDate());
                intent.putExtra("Doctor", prescriptionDetails.getDoctor());
                startActivity(intent);
            }
        });
        final TextView doctorName = (TextView) findViewById(R.id.PatientHomePageDoctorName);
        DocumentReference docRef = db.collection("Patient").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        doctorName.setText(document.getData().values().toArray()[2].toString());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String prescriptionName= patientPrescription.getSelectedItem().toString();
        for (int i=0;i<prescriptionList.size();i++){
            if (prescriptionList.get(i).getDrug().equals(prescriptionName)){
                prescriptionDetails=prescriptionList.get(i);
                break;
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}