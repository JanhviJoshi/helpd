package com.example.user.helpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    private EditText address;
    private EditText landmark;
    private EditText city;
    private EditText pincode;
    private EditText username;
    private EditText contact;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        landmark = (EditText) findViewById(R.id.landmark);
        city = (EditText) findViewById(R.id.city);
        pincode = (EditText) findViewById(R.id.pincode);
        login = (Button) findViewById(R.id.login);

        username.addTextChangedListener(loginTextWatcher);
        contact.addTextChangedListener(loginTextWatcher);

        address.addTextChangedListener(loginTextWatcher);
        landmark.addTextChangedListener(loginTextWatcher);
        city.addTextChangedListener(loginTextWatcher);
        pincode.addTextChangedListener(loginTextWatcher);

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String usernameInput = username.getText().toString().trim();
            String contactInput = contact.getText().toString().trim();

            String addressInput = address.getText().toString().trim();
            String landmarkInput = landmark.getText().toString().trim();
            String cityInput = city.getText().toString().trim();
            String pincodeInput = pincode.getText().toString().trim();
            login.setEnabled(!usernameInput.isEmpty() && !contactInput.isEmpty() && !addressInput.isEmpty() && !landmarkInput.isEmpty() && !cityInput.isEmpty() && !pincodeInput.isEmpty());
           // String ed1 = contact.getText().toString();
            //int size = ed1.length();
            //if (size < 10)
              //  Toast.makeText(getApplicationContext(), "Invalid Number", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}