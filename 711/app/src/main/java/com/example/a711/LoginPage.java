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

import org.w3c.dom.Text;

public class LoginPage extends AppCompatActivity {
    private static final String TAG = "LoginPage.java :";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Button login = (Button) findViewById(R.id.LoginPageLoginButton);
        final TextView email = (TextView) findViewById(R.id.LoginPageEmail);
        final TextView pass = (TextView) findViewById(R.id.LoginPagePassword);
        Button loginDoctor = (Button) findViewById(R.id.LoginPageDoctorLogin);
        TextView signUp = (TextView) findViewById(R.id.LoginPageSignup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, PatientSignup.class));
            }
        });
        loginDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInDoctor(email.getText().toString(),pass.getText().toString());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(email.getText().toString(), pass.getText().toString());
            }
        });

    }
    //Need to change this late!!!!!
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            Toast.makeText(this, "You Signed In successfully!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, PatientHomePage.class));
        }
        else{
            Toast.makeText(this, "Could Not Sign In!", Toast.LENGTH_LONG).show();
        }
    }
    private void updateUIDoctor(FirebaseUser currentUser){
        if (currentUser != null){
            Toast.makeText(this, "You Signed In successfully!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, DoctorHomePage.class));
        }
        else{
            Toast.makeText(this, "Could Not Sign In!", Toast.LENGTH_LONG).show();
        }
    }
    public void logIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    public void logInDoctor(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUIDoctor(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUIDoctor(null);
                        }

                        // ...
                    }
                });
    }
}