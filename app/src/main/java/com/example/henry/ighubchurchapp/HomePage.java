package com.example.henry.ighubchurchapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{

    Button showMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TableLayout tv =  this.findViewById(R.id.table);
        tv.setSelected(true);  // Set focus to the textview
        showMenu = findViewById(R.id.show_dropdown_menu);
        showMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), showMenu);
                dropDownMenu.getMenuInflater().inflate(R.menu.drop_down_menu, dropDownMenu.getMenu());
                showMenu.setText("CHURCH MENU");
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String select = menuItem.getTitle().toString();
                        if(select == "Registration"){

                        }
                        else if(select == "Login"){

                        }
                        else if(select == "Pay Tithe/Offering"){

                        }
                        else if(select == "View Pastors"){

                        }


                        Toast.makeText(getApplicationContext(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        return false;
    }
    private void inflate(){
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.selectregistration, null);
        // AlertDialog used for pop-Ups
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
        builder.setView(view);



        builder.setCancelable(false)
//                positive button is used to indicate whether to save or update
                .setPositiveButton("Register",
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


                    }
                });


    }
    }

