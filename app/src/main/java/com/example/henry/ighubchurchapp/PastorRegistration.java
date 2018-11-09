package com.example.henry.ighubchurchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class PastorRegistration extends AppCompatActivity {
    //i declare an instance of the xml id
    private EditText etsurname, etfirstname, etothername, etemail, etresidential, etphone,
            etbriefdetail;
    private Button btnregister;

    //i created a string for the xml
    private String surname, firstname, othername, email, residential, phone, briefdetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastor_registration);


        //i linked the xml with the java here
        etsurname = findViewById(R.id.etSurname);
        etfirstname = findViewById(R.id.etFirstname);
        etothername = findViewById(R.id.etOthername);
        etemail = findViewById(R.id.etEmail);
        etresidential = findViewById(R.id.etResidential);
        etphone = findViewById(R.id.etPhone);
        etbriefdetail = findViewById(R.id.etBriefDetail);

        btnregister = findViewById(R.id.btnRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


    }

    private void validate() {
        //here we typecast which means getting the Text of the user,converting to String
        // and trimming off the white spaces
        surname = etsurname.getText().toString().trim();
        firstname = etfirstname.getText().toString().trim();
        othername = etothername.getText().toString().trim();
        email = etemail.getText().toString().trim();
        residential = etresidential.getText().toString().trim();
        phone = etphone.getText().toString().trim();
        briefdetail = etbriefdetail.getText().toString().trim();



        //validation here
        if(!surname.matches("[a-zA-Z]") || surname.isEmpty()){
            etsurname.setError("This field cannot be empty");
            return;
        }
        if(!firstname.matches("[a-zA-Z]") || firstname.isEmpty()){
            etfirstname.setError("This field cannot be empty");
            return;
        }
        if(!othername.matches("[a-zA-Z]") || othername.isEmpty()){
            etothername.setError("Please input your other name(s)");
            return;
        }
        if(email.isEmpty()){
            etemail.setError("Input your email address, please");
            return;
        }
        if(residential.isEmpty()){
            etresidential.setError("Your residential address is required");
            return;
        }
        if( !Pattern.matches("[0-9]+", phone) || phone.length() != 11) {
            etphone.setError("Please enter a valid phone number");
            return;
        }
        // am_checked=0;
        if(!briefdetail.matches("[a-zA-Z]") || briefdetail.isEmpty()
                || briefdetail.length() > 200){
            etbriefdetail.setError("A brief detail about you is required");
            return;
        }else {
            Toast.makeText(this, "You have successfully filled your form",
                    Toast.LENGTH_LONG).show();

        }

    }
}

