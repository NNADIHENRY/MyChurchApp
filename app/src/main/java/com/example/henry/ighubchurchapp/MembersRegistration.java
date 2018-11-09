package com.example.henry.ighubchurchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MembersRegistration extends AppCompatActivity {
    private EditText etadminId, etsurname, etfirstname, etothername, etdate, etmonth, etyear,
            etage, etemail, etphone, etnationality, etstate, etlga, etresidential, etpermanent;
    private Button btnregister;

    private String adminId, surname, firstname, othername, date, month, year, age, email, phone,
            nationality, state, lga, residential, permanent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_registration);

        etadminId = findViewById(R.id.etAdminId);
        etsurname = findViewById(R.id.etSurname);
        etfirstname = findViewById(R.id.etFirstname);
        etothername = findViewById(R.id.etOthername);
        etdate = findViewById(R.id.etDate);
        etmonth = findViewById(R.id.etMonth);
        etyear = findViewById(R.id.etYear);
        etage = findViewById(R.id.etAge);
        etemail = findViewById(R.id.etEmail);
        etphone = findViewById(R.id.etPhone);
        etnationality = findViewById(R.id.etNationality);
        etstate = findViewById(R.id.etState);
        etlga = findViewById(R.id.etLga);
        etresidential = findViewById(R.id.etResidential);
        etpermanent = findViewById(R.id.etPermanent);


        btnregister = findViewById(R.id.btnRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


    }

    private void validate() {
        adminId = etadminId.getText().toString().trim();
        surname = etsurname.getText().toString().trim();
        firstname = etfirstname.getText().toString().trim();
        othername = etothername.getText().toString().trim();
        date = etdate.getText().toString().trim();
        month = etmonth.getText().toString().trim();
        year = etyear.getText().toString().trim();
        age = etage.getText().toString().trim();
        email = etemail.getText().toString().trim();
        phone = etphone.getText().toString().trim();
        nationality = etnationality.getText().toString().trim();
        state = etstate.getText().toString().trim();
        lga = etlga.getText().toString().trim();
        residential = etresidential.getText().toString().trim();
        permanent = etpermanent.getText().toString().trim();


        if(adminId.isEmpty()){
            etadminId.setError("this field cannot be empty");
            return;
        }

        if(Pattern.matches("[0-9]+", surname)){
            etsurname.setError("Not a valid name");
            if(surname.isEmpty()){
                etsurname.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", firstname)){
            etfirstname.setError("Not a valid name");
            if(surname.isEmpty()){
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

        if(Pattern.matches("[0-9]+", date)){
            etdate.setError("Not a valid name");
            if(date.isEmpty()){
                etdate.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", month)){
            etmonth.setError("Not a valid name");
            if(month.isEmpty()){
                etmonth.setError("this field cannot be empty");
            }
            return;
        }
        if(year.isEmpty()){
            etyear.setError("this field cannot be empty");
            return;
        }
        if(age.isEmpty()){
            etage.setError("this field cannot be empty");
            return;
        }
        if(email.isEmpty()){
            etemail.setError("this field cannot be empty");
            return;
        }
        if(phone.isEmpty()){
            etphone.setError("this field cannot be empty");
            return;
        }

        if(Pattern.matches("[0-9]+", nationality)){
            etnationality.setError("Not a valid name");
            if(nationality.isEmpty()){
                etnationality.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", state)){
            etstate.setError("Not a valid name");
            if(state.isEmpty()){
                etstate.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", lga)){
            etlga.setError("Not a valid name");
            if(lga.isEmpty()){
                etlga.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", residential)){
            etresidential.setError("Not a valid name");
            if(residential.isEmpty()){
                etresidential.setError("this field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", permanent)){
            etpermanent.setError("Not a valid name");
            if(permanent.isEmpty()){
                etpermanent.setError("this field cannot be empty");
            }
            return;
        }
        else{
            Toast.makeText(this, "Wonderful!, you have just completed your form",
                    Toast.LENGTH_SHORT).show();
        }
    }
}