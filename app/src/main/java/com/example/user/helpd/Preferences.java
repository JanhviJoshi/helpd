package com.example.user.helpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_page extends AppCompatActivity {
    private EditText username,contact,address,landmark,city,pincode;
    private Button login;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = (EditText)findViewById(R.id.username);
        contact = (EditText)findViewById(R.id.contact);
        address = (EditText)findViewById(R.id.address);
        landmark = (EditText)findViewById(R.id.landmark);
        city = (EditText)findViewById(R.id.city);
        pincode = (EditText)findViewById(R.id.pincode);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference= FirebaseDatabase.getInstance().getReference();
                String n,c,add, l,cy, p;
                n= username.getText().toString();
                c= contact.getText().toString();
                add= address.getText().toString();
                l= landmark.getText().toString();
                cy= city.getText().toString();
                p= pincode.getText().toString();
               // UserPref u= new UserPref();

                userDetails userDetails= new userDetails(n,c,add,l,cy, p);  //not sending the preferences rn
                databaseReference.child("user").setValue(userDetails); //sending the logi details to database
            }
        });


    }
}
