package com.example.a711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChooseADoctorAfterSignup extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList spinnerValueHoldValue = new ArrayList ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_a_doctor_after_signup);

        db.collection("doctors").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                Log.d("ID:", document.getId() + " => " + document.getData());
                            }
                        }
                    }
                });
        final Spinner DoctorChoice = (Spinner) findViewById(R.id.ChooseADoctorAfterSignupSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ChooseADoctorAfterSignup.this, android.R.layout.simple_list_item_1, spinnerValueHoldValue);
        DoctorChoice.setAdapter(adapter);
        final Button done = (Button) findViewById(R.id.ChooseADoctorAfterSignupDone);
        DoctorChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Doctor = DoctorChoice.getSelectedItem().toString();
                doctorChoiceForPatient(Doctor, done);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                done.setEnabled(false);
            }
        });

    }
    public void doctorChoiceForPatient(String Doctor, Button done){
        done.setEnabled(true);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}