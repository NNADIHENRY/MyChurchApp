package com.example.henry.ighubchurchapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
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
        private EditText etsurname, etfirstname, etothername, etemail, etpassword,
                etresidential, etphone;
        private Button btnregister, tvhomepage;
        private int CAMERA_PERMISSION_CODE = 24;
        private static final int CAMERA_REQUEST_CODE = 1;

        private List<String> myList;  // String list that contains file paths to images
        private GridView gridview;
        private String mCurrentPhotoPath;  // File path to the last image captured
        Button showMenu;

        //i created a string for the xml
        private String surname, firstname, othername, email, password, residential, phone;


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
            showMenu = findViewById(R.id.show_dropdown_menu);
            showMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), showMenu);
                    dropDownMenu.getMenuInflater().inflate(R.menu.drop_down_menu, dropDownMenu.getMenu());
                    showMenu.setText("...");
                    dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            boolean me;
                            if(menuItem.getTitle().toString().matches("Registration") ){

                                showDialog();
                                me = true;

                            }
                            else if(menuItem.getTitle().toString().matches("Login") ){
                                me = true;

                            }
                            else if(menuItem.getTitle().toString().matches("Pay Tithe/Offering")){
                                me = true;


                            }
                            else if(menuItem.getTitle().toString().matches( "View Pastors")){
                                me = true;

                            }
                            else if(menuItem.getTitle().toString().matches( "Weekly Activity")){
                                weeklyActivity();
                                me = true;

                            }
                            else {
                                me = false;
                            }
                            return me;



                        }
                    });
                    dropDownMenu.show();
                }
            });



        }
        private void assign(){
            etsurname = findViewById(R.id.etSurname);
            etfirstname = findViewById(R.id.etFirstname);
            etothername = findViewById(R.id.etOthername);
            etemail = findViewById(R.id.etEmail);
            etpassword = findViewById(R.id.etPassword);
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
            password = etpassword.getText().toString().trim();
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
            if(password.isEmpty() || password.length() < 6){
                etpassword.setError("Please input your password");
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
        private void weeklyActivity() {
            // Override active layout
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.weekly_activity, null);
            // AlertDialog used for pop-Ups
            AlertDialog.Builder builder = new AlertDialog.Builder(PastorRegistration.this);
            builder.setView(view);

            builder.setCancelable(true)
//                positive button is used to indicate whether to save or update
                    .setPositiveButton( "Continue",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogBox, int id) {
                                }
                            })
                    // Used to set Negative button to cancel
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogBox, int id) {
                            dialogBox.cancel();

                        }
                    });

            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener
                    (new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();

                        }
                    });


        }
        private void showDialog() {
            // Override active layout
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.selectregistration, null);
            // AlertDialog used for pop-Ups
            AlertDialog.Builder builder = new AlertDialog.Builder(PastorRegistration.this);
            builder.setView(view);

            // Used to link or get views in the dialogBox
            TextView text = view.findViewById(R.id.reg);
            final RadioButton pastor = view.findViewById(R.id.btnpastor);
            final RadioButton member = view.findViewById(R.id.btnmember);

            builder.setCancelable(false)
//                positive button is used to indicate whether to save or update
                    .setPositiveButton( "Continue",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogBox, int id) {
                                }
                            })
                    // Used to set Negative button to cancel
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogBox, int id) {
                            dialogBox.cancel();

                        }
                    });

            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener
                    (new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Show toast message when no text is entered

                            if (pastor.isChecked()==false && member.isChecked()==false){

                                Toast.makeText(PastorRegistration.this, " select registration type ",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(member.isChecked()==true) {
                                startActivity(new Intent(PastorRegistration.this, MembersRegistration.class));
                            }else if(pastor.isChecked()==true) {
                                startActivity(new Intent(PastorRegistration.this, PastorRegistration.class));
                            }
                            else {
                                alertDialog.dismiss();
                            }

                            // check if user updating note


                        }
                    });


        }


    }


