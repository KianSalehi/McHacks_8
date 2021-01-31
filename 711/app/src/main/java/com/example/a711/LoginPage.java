package com.example.a711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginPage extends AppCompatActivity {
    /***********************DOWN IS FALL DETECTION*************************************/
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    private float mOrientation;
    private Boolean noFall=false;
    private final SensorEventListener mSensorListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            Log.d("z= ", ((Float)se.values[2]).toString());
            Log.d("Accel", ((Float) mAccel).toString());
            if(mAccel>7||mAccel<-7) {
                for (int i = 0; i <= 5; i++) {
                    if (se.values[2] > 7.5|| se.values[2] < -7.5) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }

                    } else {
                        noFall=true;
                        break;
                    }
                }
                if(noFall==true) {
                    noFall=false;
                }
                else{Log.d("Message", "FALL ALERT!!");}
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
    /***********************UP IS FALL DETECTION*************************************/

    private static final String TAG = "LoginPage.java :";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /***************************DOWN IS FALL DETECTION ******************************/
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);//CECI EST RELIÉ AU FALL DETECTION
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mOrientation = SensorManager.GRAVITY_EARTH;
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;//JUSQU'ICI
        /*****************************UP IS FALL DETECTION********************************/
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


        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }