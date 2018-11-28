package com.example.henry.ighubchurchapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PastorLoginPage extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnSubmit, btnSignUp, btnForgotten;
    private FirebaseAuth firebaseAuth;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login_page);

        firebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnForgotten = findViewById(R.id.btnForgotten);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnForgotten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PastorLoginPage.this, MainActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new Intent(PastorLoginPage.this,
                        PastorRegistration.class) ));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });



    }

    private void validate() {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("Please input a valid email");
        }
        if (password.isEmpty()) {
            etPassword.setError("Incorrect Password");
        } else{
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(PastorLoginPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(PastorLoginPage.this,
                                    "pastors login " + task.isSuccessful(), Toast.LENGTH_LONG).show();
                            if (!task.isSuccessful()) {
                                Toast.makeText(PastorLoginPage.this,
                                        "Authentication faild." + task.getException(), Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(PastorLoginPage.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
        }
        }
    }


