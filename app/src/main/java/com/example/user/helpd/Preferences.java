package com.example.user.helpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Preferences extends AppCompatActivity {

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioButton rb_clean;
    private RadioButton rb_wash;
    private RadioButton rb_cook;
    private RadioButton rb_morn;
    private RadioButton rb_eve;
    private Button button, signout;
    private Button login;
    private DatabaseReference databaseReference;
    private String code;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);


        radioGroup1= (RadioGroup)findViewById(R.id.rad_help_pref);
        radioGroup2= (RadioGroup)findViewById(R.id.rad_time_pref);
        rb_clean= (RadioButton) findViewById(R.id.clean);
        rb_cook= (RadioButton)findViewById(R.id.cook);
        rb_wash= (RadioButton)findViewById(R.id.wash);
        rb_eve=(RadioButton)findViewById(R.id.eve);
        rb_morn=(RadioButton)findViewById(R.id.morn);
        button= (Button)findViewById(R.id.button);
        login= (Button)findViewById(R.id.login);
        signout= findViewById(R.id.signout);

        /**** getting data from intent *****/
        Bundle data= getIntent().getExtras();
       final String uniqueKey= FirebaseAuth.getInstance().getUid();


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses submit button
                /*** Getting help preference ***/
               databaseReference = FirebaseDatabase.getInstance().getReference();
                int id1= radioGroup1.getCheckedRadioButtonId();

                switch(id1)
                {
                    case R.id.cook: code= "cook";
                                    break;
                    case R.id.clean: code="clean";
                                    break;
                    case R.id.wash: code= "wash";
                                    break;
                }

                /*** getting time pref ***/
               int id2= radioGroup2.getCheckedRadioButtonId();
                switch(id2)
                {
                    case R.id.morn: time= 1;
                        break;
                    case R.id.eve: time= 7;
                        break;

                }

                UserDetails user2= new UserDetails();
                user2.setServices(code);
                user2.setTimings(time);

               //UserDetails user2= new UserDetails(code, time);
                databaseReference.child("user").child(uniqueKey).child("preferences").setValue(user2);

                Intent i= new Intent(Preferences.this, Available.class);
                i.putExtra("preference",user2.getServices());
                i.putExtra("timings", user2.getTimings());
                i.putExtra("uniqueKey", uniqueKey);
                startActivity(i);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Preferences.this, "Signed Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Preferences.this, LoginActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
