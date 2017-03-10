package com.example.saloni.placementv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity implements View.OnClickListener{


    private Button buttonAddJobProfile;
    private Button buttonViewAllJobProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonAddJobProfile = (Button)findViewById(R.id.buttonAddJobProfile);
        buttonViewAllJobProfiles = (Button)findViewById(R.id.buttonViewAllJobProfiles);

        buttonAddJobProfile.setOnClickListener(this);
        buttonViewAllJobProfiles.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view== buttonAddJobProfile)
        {

            Intent myIntent = new Intent(HomePage.this, AddJobProfile.class);
            HomePage.this.startActivity(myIntent);
        }

        else if(view == buttonViewAllJobProfiles)
        {

            Intent myIntent = new Intent(HomePage.this, GetJobProfiles.class);
            HomePage.this.startActivity(myIntent);
        }

    }

}
