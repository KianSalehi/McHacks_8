package com.example.a711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ChooseADoctorAfterSignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Map<String, Object> userMap = new HashMap<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> spinnerValueHoldValue = new ArrayList ();
    ArrayList<Doctor> listDoctor = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner DoctorChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_a_doctor_after_signup);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerValueHoldValue);
        db.collection("doctors").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                Doctor doctor = new Doctor(document.getId(), document.getData().values().toArray()[0].toString());
                                listDoctor.add(doctor);
                                spinnerValueHoldValue.add(doctor.getName());
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        DoctorChoice =(Spinner) findViewById(R.id.ChooseADoctorAfterSignupSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DoctorChoice.setAdapter(adapter);
        DoctorChoice.setSelection(1);
        DoctorChoice.setOnItemSelectedListener(this);
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
        String nameDoctor= DoctorChoice.getSelectedItem().toString();
        Doctor patientDoctor = null;
        for(int i=0;i<listDoctor.size();i++){
            if (listDoctor.get(i).getName().equals(nameDoctor)){
                patientDoctor = listDoctor.get(i);
                break;
            }
        }
        userMap.put("Doctor", patientDoctor.getName());
        userMap.put("Doctor ID", patientDoctor.getId());
        userMap.put("ID", user.getUid());
        userMap.put("Name", user.getDisplayName());
        userMap.put("Email", user.getEmail());


        Toast.makeText(parent.getContext(), "Selected: " + nameDoctor, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}