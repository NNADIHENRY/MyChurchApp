package com.example.henry.ighubchurchapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class PastorRegistration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //i declare an instance of the xml id
    private EditText etsurname, etfirstname, etothername, etemail, etresidential, etphone,
            etbriefdetail;
    private TextView tvhomepage;
    private Button btnregister;

    //i created a string for the xml
    private String surname, firstname, othername, email, residential, phone, briefdetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastor_registration);
//        mAuth = FirebaseAuth.getInstance();


        //i linked the xml with the java here
        etsurname = findViewById(R.id.etSurname);
        etfirstname = findViewById(R.id.etFirstname);
        etothername = findViewById(R.id.etOthername);
        etemail = findViewById(R.id.etEmail);
        etresidential = findViewById(R.id.etResidential);
        etphone = findViewById(R.id.etPhone);
        etbriefdetail = findViewById(R.id.etBriefDetail);
        tvhomepage = findViewById(R.id.tvHomePage);
        btnregister = findViewById(R.id.btnRegister);

        tvhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PastorRegistration.this,
                        HomePage.class));
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

//    private void updateUI(FirebaseUser currentUser) {
//    }
//    mAuth.createUserWithEmail(email)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "createUserWithEmail:success");
//                FirebaseUser user = mAuth.getCurrentUser();
//                updateUI(user);
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                Toast.makeText(PastorRegistration.this, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show();
//                updateUI(null);
//            }
//
//        }
//    });
//
//
//    mAuth.signInWithEmail(email)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "signInWithEmail:success");
//                FirebaseUser user = mAuth.getCurrentUser();
//                updateUI(user);
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                Toast.makeText(PastorRegistration.this, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show();
//                updateUI(null);
//            }
//
//            // ...
//        }
//    });
//
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null) {
//        // Name, email address, and profile photo Url
//        String name = user.getDisplayName();
//        String email = user.getEmail();
//        Uri photoUrl = user.getPhotoUrl();
//
//        // Check if user's email is verified
//        boolean emailVerified = user.isEmailVerified();
//
//        // The user's ID, unique to the Firebase project. Do NOT use this value to
//        // authenticate with your backend server, if you have one. Use
//        // FirebaseUser.getToken() instead.
//        String uid = user.getUid();
//    }

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

        if(Pattern.matches("[0-9]+", surname)){
            etsurname.setError("Not a valid name");
            if(surname.isEmpty()){
                etsurname.setError("This field cannot be empty");
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

        if(Pattern.matches("[0-9]+", othername)){
            etothername.setError("Not a valid name");
            if(othername.isEmpty()){
                etothername.setError("this field cannot be empty");
            }
            return;
        }

        if(email.isEmpty()){
            etemail.setError("Input your email address, please");
            return;
        }

        if(Pattern.matches("[0-9]+", residential)){
            etresidential.setError("Not a valid name");
            if(residential.isEmpty()){
                etresidential.setError("this field cannot be empty");
            }
            return;
        }

        if( !Pattern.matches("[0-9]+", phone) || phone.length() != 11) {
            etphone.setError("Please enter a valid phone number");
            return;
        }

        if(!Pattern.matches("[a-zA-Z]+", briefdetail) || briefdetail.isEmpty()
                || briefdetail.length() > 200){
            etbriefdetail.setError("A brief detail about you is required");

        }else {
            Toast.makeText(this, "You have successfully filled your form",
                    Toast.LENGTH_LONG).show();

        }

    }
}

