package com.example.a711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class PatientSignup extends AppCompatActivity {
    private static final String TAG = "PatientSignup.java :";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_patient_signup);
        Button signUp= (Button) findViewById(R.id.PatientSignupButton);
        final TextView email = (TextView) findViewById(R.id.PatientSignupEmail);
        final TextView password = (TextView) findViewById(R.id.PatientSignupPass);
        final TextView name = (TextView) findViewById(R.id.PatientSignupFirstLastName);
        TextView logIn = (TextView) findViewById(R.id.PatientSignupLogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientSignup.this, LoginPage.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(email.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        });
    }
//    public void onStart() {
  //      super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
   //     FirebaseUser currentUser = mAuth.getCurrentUser();
 //       updateUI(currentUser);
 //   }

    private void updateUI(FirebaseUser currentUser, String name) {
        //Switch to homepage for patients
        if (currentUser!=null){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();
            currentUser.updateProfile(profileUpdates);
            Toast.makeText(this, "You Signed In successfully!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(PatientSignup.this, ChooseADoctorAfterSignup.class));
    }
        else{
            Toast.makeText(this, "Something went wrong please check your email and password", Toast.LENGTH_SHORT).show();
        }
    }

    //Sign up function
    public void signUp(String email, String password, final String name){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(PatientSignup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user, name);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(PatientSignup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null, null);
                        }

                    }
                });
    }
}