package com.example.henry.ighubchurchapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


    public class PastorRegistration extends AppCompatActivity {


        //i declare an instance of the xml id
        private EditText etsurname, etfirstname, etothername, etemail, etresidential, etphone;
        private Button btnregister, tvhomepage;
        private int CAMERA_PERMISSION_CODE = 24;
        private static final int CAMERA_REQUEST_CODE = 1;

        private List<String> myList;  // String list that contains file paths to images
        private GridView gridview;
        private String mCurrentPhotoPath;  // File path to the last image captured


        //i created a string for the xml
        private String surname, firstname, othername, email, residential, phone;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pastor_registration);


            //i linked the xml with the java here
            tvhomepage = findViewById(R.id.tvHomePage);
            btnregister = findViewById(R.id.btnRegister);

//            tvhomepage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(PastorRegistration.this,
//                            HomePage.class));
//                }
//            });


            btnregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assign();
                    validate();

                    if(validate()) {


                        // Write a message to the database
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("PastorRegistration");

                        myRef.setValue(surname);
                        myRef.setValue(firstname);
                        myRef.setValue(othername);
                        myRef.setValue(email);
                        myRef.setValue(residential);
                        myRef.setValue(phone);
                    }else {

                    }
                }
            });


        }
        private void assign(){
            etsurname = findViewById(R.id.etSurname);
            etfirstname = findViewById(R.id.etFirstname);
            etothername = findViewById(R.id.etOthername);
            etemail = findViewById(R.id.etEmail);
            etresidential = findViewById(R.id.etResidential);
            etphone = findViewById(R.id.etPhone);
        }

        private boolean validate() {
            //here we typecast which means getting the Text of the user,converting to String
            // and trimming off the white spaces
            surname = etsurname.getText().toString().trim();
            firstname = etfirstname.getText().toString().trim();
            othername = etothername.getText().toString().trim();
            email = etemail.getText().toString().trim();
            residential = etresidential.getText().toString().trim();
            phone = etphone.getText().toString().trim();

            //validation here

            if (Pattern.matches("[0-9]+", surname)) {
                etsurname.setError("Not a valid name");
                if (surname.isEmpty()) {
                    etsurname.setError("This field cannot be empty");
                }
                return false;
            }

            if (Pattern.matches("[0-9]+", firstname)) {
                etfirstname.setError("Not a valid name");
                if (firstname.isEmpty()) {
                    etfirstname.setError("this field cannot be empty");
                }
                return false;
            }

            if (Pattern.matches("[0-9]+", othername)) {
                etothername.setError("Not a valid name");
                if (othername.isEmpty()) {
                    etothername.setError("this field cannot be empty");
                }
                return false;
            }

            if (email.isEmpty()) {
                etemail.setError("Input your email address, please");
                return false;
            }

            if (Pattern.matches("[0-9]+", residential)) {
                etresidential.setError("Not a valid name");
                if (residential.isEmpty()) {
                    etresidential.setError("this field cannot be empty");
                }
                return false;
            }

            if (!Pattern.matches("[0-9]+", phone) || phone.length() != 11) {
                etphone.setError("Please enter a valid phone number");
                return false;
            } else {
                Toast.makeText(this, "You have successfully filled your form",
                        Toast.LENGTH_LONG).show();
                Toast.makeText(this, "sending to database...",
                        Toast.LENGTH_LONG).show();
                return true;

            }

        }
            }


