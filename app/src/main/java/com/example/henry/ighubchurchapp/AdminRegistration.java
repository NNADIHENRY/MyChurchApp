package com.example.henry.ighubchurchapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class AdminRegistration extends AppCompatActivity {
    private EditText etsurname, etfirstname, etemail, etphone;
    private Button btnsubmit, tvhomepage;
    double id;


    private String surname, firstname, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);
        id = Math.random();

        //linking the xml and java
        etsurname = findViewById(R.id.etSurname);
        etfirstname = findViewById(R.id.etFirstname);
        etemail = findViewById(R.id.etEmail);
        etphone = findViewById(R.id.etPhone);
        tvhomepage = findViewById(R.id.tvHomePage);
        btnsubmit = findViewById(R.id.btnSubmit);

        tvhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRegistration.this,
                        HomePage.class));
            }
        });


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("AdminRegistration");

                myRef.setValue(surname);
                myRef.setValue(firstname);
                myRef.setValue(email);
                myRef.setValue(phone);
                myRef.setValue(id);

            }
        });
        Toast.makeText(this, "your admin id isv "+id, Toast.LENGTH_LONG).show();
    }

    private void validate() {
        //validation here
        surname = etsurname.getText().toString().trim();
        firstname = etfirstname.getText().toString().trim();
        email = etemail.getText().toString().trim();
        phone = etphone.getText().toString().trim();


        if(Pattern.matches("[0-9]+", surname)){
            etsurname.setError("Not a valid name");
            if(surname.isEmpty()){
                etsurname.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", firstname)){
            etfirstname.setError("Not a valid name");
            if(firstname.isEmpty()){
                etfirstname.setError("this field cannot be empty");
            }
            return;
        }
        if(email.isEmpty()){
            etemail.setError("Please input a valid email address");
            return;
        }
        if(!Pattern.matches("[0-9]+", phone) || phone.length() != 11) {
            etphone.setError("Please enter a valid phone number");
           
        }else{
            Toast.makeText(this,"DETAILS COMPLETE", Toast.LENGTH_LONG).show();
        }

    }
}
