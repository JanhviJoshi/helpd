package com.example.user.helpd;

import android.app.ProgressDialog;
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
    private RadioButton rb_dust;
    private RadioButton rb_9_10;
    private RadioButton rb_8_9, rb_10_11, rb_11_12, rb_3_4, rb_4_5, rb_5_6, rb_6_7;
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
        rb_clean= (RadioButton) findViewById(R.id.cleaning_radiobutton);
        rb_cook= (RadioButton)findViewById(R.id.cooking_radiobutton);
        rb_wash= (RadioButton)findViewById(R.id.washing_radiobutton);
        rb_dust= (RadioButton)findViewById(R.id.dusting_radiobutton);

        rb_8_9=(RadioButton)findViewById(R.id.radioButton_8to9);
        rb_9_10=(RadioButton)findViewById(R.id.radioButton_9to10);
        rb_10_11= findViewById(R.id.radioButton_10to11);
        rb_11_12= findViewById(R.id.radioButton_11to12);
        rb_3_4= findViewById(R.id.radioButton_3to4);
        rb_4_5= findViewById(R.id.radioButton_4to5);
        rb_5_6= findViewById(R.id.radioButton_5to6);
        rb_6_7= findViewById(R.id.radioButton_6to7);


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
                int id2= radioGroup2.getCheckedRadioButtonId();
                System.out.println("VALUES OF ID1 ID2" +id1+" "+id2);

                if(id1!=-1 && id2!=-1) {
                    switch (id1) {
                        case R.id.cooking_radiobutton:
                            code = "cook";
                            break;
                        case R.id.cleaning_radiobutton:
                            code = "clean";
                            break;
                        case R.id.washing_radiobutton:
                            code = "wash";
                            break;
                        case R.id.dusting_radiobutton:
                            code = "dust";
                            break;
                    }

                    /*** getting time pref ***/

                    switch (id2) {
                        case R.id.radioButton_3to4:
                            time = 1;
                            break;
                        case R.id.radioButton_4to5:
                            time = 2;
                            break;
                        case R.id.radioButton_5to6:
                            time = 3;
                            break;
                        case R.id.radioButton_6to7:
                            time = 4;
                            break;
                        case R.id.radioButton_8to9:
                            time = 5;
                            break;
                        case R.id.radioButton_9to10:
                            time = 6;
                            break;
                        case R.id.radioButton_10to11:
                            time = 7;
                            break;

                    }

                    UserPref user2 = new UserPref();
                    user2.setHelpPref(code);
                    user2.setTimings(time);

                    //UserDetails user2= new UserDetails(code, time);
                    databaseReference.child("user").child(uniqueKey).child("preferences").setValue(user2);

                    Intent i = new Intent(Preferences.this, Available.class);
                    i.putExtra("preference", user2.getHelpPref());
                    i.putExtra("timings", user2.getTimings());
                    i.putExtra("uniqueKey", uniqueKey);
                    startActivity(i);
                }else
                    Toast.makeText(Preferences.this, "Please Enter Full Details", Toast.LENGTH_SHORT).show();
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
