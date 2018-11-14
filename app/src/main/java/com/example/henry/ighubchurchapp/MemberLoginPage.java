package com.example.henry.ighubchurchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemberLoginPage extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnSubmit, btnSignUp;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login_page);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new Intent(MemberLoginPage.this,
                        MembersRegistration.class) ));
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
            Toast.makeText(this, "Welcome to your page", Toast.LENGTH_LONG).show();
        }
    }
}
