package com.example.henry.ighubchurchapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    EditText etemail, etpassword;
    String email, password;
    Button fpassword, login;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        firebaseAuth = FirebaseAuth.getInstance();


        fpassword = findViewById(R.id.btnForgotten);
        fpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, MainActivity.class));
            }
        });

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etemail = findViewById(R.id.et_email);
                etpassword = findViewById(R.id.etpassword);

                email = etemail.getText().toString().trim();
                password = etpassword.getText().toString().trim();


                if (email.isEmpty() == true) {
                    etemail.setError("email can't be empty");

                } else if (password.isEmpty() == true) {
                    etpassword.setError("password can't be empty");
                } else {

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(LoginPage.this,
                                            "createUserWithEmail:onComplete" + task.isSuccessful(), Toast.LENGTH_LONG).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginPage.this,
                                                "Authentication faild." + task.getException(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(LoginPage.this, ProfileActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }

            }
        });

    }
}