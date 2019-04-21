package com.example.user.helpd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login Activity";
    EditText e, pw;
    Button login, Signup;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"on create called");
        setContentView(R.layout.activity_login);

        e= findViewById(R.id.email);
        pw= findViewById(R.id.password);
        login= findViewById(R.id.login);
        Signup= findViewById(R.id.signup);

        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null) {
            //start new Activity which is to be opened after successful login
            Log.i(TAG,"User already logged in");
            showToast("User already logged in");
            startActivity(new Intent(this,Preferences.class));
        } else {
            Log.i(TAG,"User not logged in");
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
                    progress.setCancelable(true);
                    progress.setTitle("Logging in..");
                    progress.show();
                    String email = e.getText().toString();
                    String password = pw.getText().toString();


                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        progress.dismiss();
                                        updateUi(user);
                                    } else {
                                        progress.dismiss();
                                        updateUi(null);

                                    }
                                }
                            });
                }
            });

            Signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //start sign up activity
                    startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

    }

    void showLoginPage(boolean show) {
        e.setVisibility(show? View.VISIBLE:View.INVISIBLE);
        pw.setVisibility(show? View.VISIBLE:View.INVISIBLE);
        login.setVisibility(show? View.VISIBLE:View.INVISIBLE);

    }

    public void updateUi(FirebaseUser user){
        if(user!=null){
            showToast("Login Successful!");
            startActivity(new Intent(LoginActivity.this, Preferences.class));
        }else {
            showToast("Invalid Credentials. Try Again");
            showLoginPage(true);
        }
    }

    public void showToast(String msg){
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
