package com.example.saloni.placementv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddJobProfile extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase;
    private DatabaseReference jDatabase;

    private EditText editTextCompanyName;
    private EditText editTextDesignation;
    private EditText editTextLocation;
    private EditText editTextCompensationBTech;
    private EditText editTextCompensationMTech;
    private EditText editTextPrePlacementTalk;
    private EditText editTextEventDate;
    private EditText editTextComments;

    private Button buttonAddJob;

    String JID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonAddJob =(Button) findViewById(R.id.buttonAddJob);
        buttonAddJob.setOnClickListener(this);

        //creating Firebase Database instances here
        mDatabase = FirebaseDatabase.getInstance().getReference();
        jDatabase = FirebaseDatabase.getInstance().getReference("JobProfiles");
    }

    @Override
    public void onClick(View view) {

        //Add job button
        if(view == buttonAddJob)

        {

            JID = jDatabase.push().getKey();
            JobProfile newJobProfile = createProfileDetails();
            jDatabase.child(JID).setValue(newJobProfile);

            Toast.makeText(this, "Job Profile Added!",Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(AddJobProfile.this, GetJobProfiles.class);
            AddJobProfile.this.startActivity(myIntent);


        }


    }

    private JobProfile createProfileDetails()
    {

        editTextCompanyName = (EditText) findViewById(R.id.editTextCompanyName);
        editTextDesignation = (EditText) findViewById(R.id.editTextDesignation);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextCompensationBTech = (EditText) findViewById(R.id.editTextCompensationBTech);
        editTextCompensationMTech = (EditText) findViewById(R.id.editTextCompensationMTech);
        editTextPrePlacementTalk = (EditText) findViewById(R.id.editTextPrePlacementTalk);
        editTextEventDate = (EditText) findViewById(R.id.editTextEventDate);
        editTextComments = (EditText) findViewById(R.id.editTextComments);

        String companyName= editTextCompanyName.getText().toString().trim();
        String designation= editTextDesignation.getText().toString().trim();
        String location= editTextLocation.getText().toString().trim();
        int compensationBTech= Integer.parseInt(editTextCompensationBTech.getText().toString().trim());
        int compensationMTech= Integer.parseInt(editTextCompensationMTech.getText().toString().trim());
        String prePlacementTalk= editTextPrePlacementTalk.getText().toString().trim();
        String eventDate= editTextEventDate.getText().toString().trim();
        String comments= editTextComments.getText().toString().trim();


        JobProfile newJobProfile = new JobProfile(JID,companyName, designation, location, compensationBTech,
                compensationMTech, prePlacementTalk, eventDate, comments);


        return newJobProfile;

    }

}
