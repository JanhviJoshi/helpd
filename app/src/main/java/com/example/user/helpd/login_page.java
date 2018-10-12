package com.example.user.helpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class login_page extends AppCompatActivity {
    EditText username,contact,address,landmark,city,pincode;
    Button login;
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
    }
}
