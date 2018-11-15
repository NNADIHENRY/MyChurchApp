package com.example.henry.ighubchurchapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.regex.Pattern;

public class MembersRegistration extends AppCompatActivity {
    private EditText etadminId, etsurname, etfirstname, etothername, etdate, etmonth, etyear,
            etage, etemail, etpassword, etphone, etnationality, etstate, etlga, etresidential,
            etpermanent;
    private Button btnregister, tvhomepage;
    Button showMenu;
    ImageButton login, register, pastor, tithe, about, help, weeklyactivity;


    private String adminId, surname, firstname, othername, date, month, year, age, email, password,
            phone,
            nationality, state, lga, residential, permanent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_registration);



        tvhomepage = findViewById(R.id.tvHomePage);
        btnregister = findViewById(R.id.btnRegister);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assign();
                validate();


                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("MemberRegistration");

                myRef.setValue(adminId);
                myRef.setValue(surname);
                myRef.setValue(firstname);
                myRef.setValue(othername);
                myRef.setValue(date);
                myRef.setValue(month);
                myRef.setValue(year);
                myRef.setValue(age);
                myRef.setValue(email);
                myRef.setValue(phone);
                myRef.setValue(nationality);
                myRef.setValue(state);
                myRef.setValue(lga);
                myRef.setValue(residential);
                myRef.setValue(permanent);


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
//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//
//        return false;
//    }





    private void assign(){
        etadminId = findViewById(R.id.etAdminId);
        etsurname = findViewById(R.id.etSurname);
        etfirstname = findViewById(R.id.etFirstname);
        etothername = findViewById(R.id.etOthername);
        etdate = findViewById(R.id.etDate);
        etmonth = findViewById(R.id.etMonth);
        etyear = findViewById(R.id.etYear);
        etage = findViewById(R.id.etAge);
        etemail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etPassword);
        etphone = findViewById(R.id.etPhone);
        etnationality = findViewById(R.id.etNationality);
        etstate = findViewById(R.id.etState);
        etlga = findViewById(R.id.etLga);
        etresidential = findViewById(R.id.etResidential);
        etpermanent = findViewById(R.id.etPermanent);
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
        password = etpassword.getText().toString().trim();
        phone = etphone.getText().toString().trim();
        nationality = etnationality.getText().toString().trim();
        state = etstate.getText().toString().trim();
        lga = etlga.getText().toString().trim();
        residential = etresidential.getText().toString().trim();
        permanent = etpermanent.getText().toString().trim();


        if(adminId.isEmpty()){
            etadminId.setError("PLease input your admin id");
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

        if(!Pattern.matches("[0-9]+", date) || date.length() > 2){
            etdate.setError("Not a valid name");
            if(date.isEmpty()){
                etdate.setError("This field cannot be empty");
            }
            return;
        }

        if(Pattern.matches("[0-9]+", month) || month.length() > 3){
            etmonth.setError("Not a valid name");
            if(month.isEmpty()){
                etmonth.setError("this field cannot be empty");
            }
            return;
        }

        if(!Pattern.matches("[0-9]+", year) || year.length() > 4){
            etyear.setError("Not a valid name");
            if(year.isEmpty()){
                etyear.setError("this field cannot be empty");
            }
            return;
        }

        if(!Pattern.matches("[0-9]+", age) || age.length() > 3){
            etage.setError("Not a valid name");
            if(age.isEmpty()){
                etage.setError("This field cannot be empty");
            }
            return;
        }

        if(email.isEmpty()){
            etemail.setError("This field cannot be empty");
            return;
        }
        if(password.isEmpty() || password.length() < 6){
            etpassword.setError("Please input your password");
            return;
        }

        if(!Pattern.matches("[0-9]+", phone) || phone.length() != 11) {
            etphone.setError("Please enter a valid phone number");
            if(phone.isEmpty()){
                etphone.setError("This field cannot be empty");
            }
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
            //return statement is not required here because the method is void
        }
        else{
            Toast.makeText(this, "Wonderful!, you have just completed your form",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void weeklyActivity() {
        // Override active layout
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.weekly_activity, null);
        // AlertDialog used for pop-Ups
        AlertDialog.Builder builder = new AlertDialog.Builder(MembersRegistration.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MembersRegistration.this);
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

                            Toast.makeText(MembersRegistration.this, " select registration type ",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(member.isChecked()==true) {
                            startActivity(new Intent(MembersRegistration.this, MembersRegistration.class));
                        }else if(pastor.isChecked()==true) {
                            startActivity(new Intent(MembersRegistration.this, PastorRegistration.class));
                        }
                        else {
                            alertDialog.dismiss();
                        }

                        // check if user updating note


                    }
                });


    }


}