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
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henry.ighubchurchapp.notespad.NotesPad;
import com.example.henry.ighubchurchapp.video.VideoAppMainActivity;

public class HomePage extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{

    Button showMenu;
    ImageButton login, register, pastor, tithe, about, help, weeklyactivity, video, note, livevideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TableLayout tv =  this.findViewById(R.id.table);
       // Set focus to the textview
        livevideo = findViewById(R.id.btnvideo);
        livevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, VideoAppMainActivity.class));

            }
        });

        note = findViewById(R.id.btnnote);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notes = new Intent(HomePage.this, NotesPad.class);
                startActivity(notes);
            }
        });
        tithe = findViewById(R.id.btnTithe);
        tithe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        pastor = findViewById(R.id.btnPastors);
        pastor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, PastorPage.class));
            }
        });


        about = findViewById(R.id.btnAbout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();

            }
        });



        help = findViewById(R.id.btnHelp);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help();
            }
        });


        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();


            }
        });




        register = findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();


            }
        });




        weeklyactivity = findViewById(R.id.btnWeeklyActivity);
        weeklyactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weeklyActivity();
            }
        });
        video = findViewById(R.id.btnVideoStreeming);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, VideoStreeming.class));
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
                            login();
                            me = true;

                        }
                        else if(menuItem.getTitle().toString().matches("Pay Tithe/Offering")){
                            me = true;


                        }
                        else if(menuItem.getTitle().toString().matches( "View Pastors")){
                            startActivity(new Intent(HomePage.this, PastorPage.class));
                            me = true;

                        }
                        else if(menuItem.getTitle().toString().matches( "Weekly Activity")){
                            weeklyActivity();
                            me = true;

                        }
                        else if(menuItem.getTitle().toString().matches("Help") ) {
                            help();
                            me = true;
                        }else if(menuItem.getTitle().toString().matches("About Us") ) {
                            about();
                            me = true;
                        }else if(menuItem.getTitle().toString().matches("Take Note")){
                            Intent notes = new Intent(HomePage.this, NotesPad.class);
                            startActivity(notes);
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



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        return false;
    }
    private void showDialog() {
        // Override active layout
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.selectregistration, null);
        // AlertDialog used for pop-Ups
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
        builder.setView(view);

        // Used to link or get views in the dialogBox
        TextView text = view.findViewById(R.id.reg);
        final RadioButton pastor = view.findViewById(R.id.btnpastor);
        final RadioButton member = view.findViewById(R.id.btnmember);
        final RadioButton admin = view.findViewById(R.id.btnAdmin);

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

                        if (pastor.isChecked()==false && member.isChecked()==false && admin.isChecked()==false){

                            Toast.makeText(HomePage.this, " select registration type ",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(member.isChecked()==true) {
                            startActivity(new Intent(HomePage.this, MembersRegistration.class));
                        }else if(pastor.isChecked()==true) {
                            startActivity(new Intent(HomePage.this, PastorRegistration.class));
                        }else if(admin.isChecked()==true) {
                            startActivity(new Intent(HomePage.this, AdminRegistration.class));
                        }
                        else {
                            alertDialog.dismiss();
                        }

                        // check if user updating note


                    }
                });


    }
    private void weeklyActivity() {
        // Override active layout
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.weekly_activity, null);
        // AlertDialog used for pop-Ups
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
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
    private void about(){
    // Override active layout
    LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
    View view = layoutInflater.inflate(R.layout.activity_about, null);
    // AlertDialog used for pop-Ups
    AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
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


    private void help(){
        // Override active layout
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.activity_contact, null);
        // AlertDialog used for pop-Ups
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
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


    private void login(){
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.selectregistration, null);
        // AlertDialog used for pop-Ups
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
        builder.setView(view);

        // Used to link or get views in the dialogBox
        TextView text = view.findViewById(R.id.reg);
        final RadioButton pastor = view.findViewById(R.id.btnpastor);
        final RadioButton member = view.findViewById(R.id.btnmember);
        final RadioButton admin = view.findViewById(R.id.btnAdmin);

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

                        if (pastor.isChecked()==false && member.isChecked()==false && admin.isChecked()==false){

                            Toast.makeText(HomePage.this, " select registration type ",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(member.isChecked()==true) {
                            startActivity(new Intent(HomePage.this, MemberLoginPage.class));
                        }else if(pastor.isChecked()==true) {
                            startActivity(new Intent(HomePage.this, PastorLoginPage.class));
                        }else if(admin.isChecked()==true) {
                            startActivity(new Intent(HomePage.this, LoginPage.class));
                        }
                        else {
                            alertDialog.dismiss();
                        }

                        // check if user updating note


                    }
                });
    }
    }

