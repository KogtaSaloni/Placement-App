package com.example.saloni.placementv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditJobProfile extends AppCompatActivity implements View.OnClickListener

{

    EditText editTextCompanyName;
    EditText editTextDesignation;
    EditText editTextLocation;
    EditText editTextCompensationBTech;
    EditText editTextCompensationMTech;
    EditText editTextPrePlacementTalk;
    EditText editTextEventDate;
    EditText editTextComments;

    Button buttonSaveChanges;

    String JID;

    private DatabaseReference jDatabase;
    private DatabaseReference jobObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextCompanyName = (EditText) findViewById(R.id.editTextCompanyName);
        editTextDesignation = (EditText)findViewById(R.id.editTextDesignation);
        editTextLocation = (EditText)findViewById(R.id.editTextLocation);
        editTextCompensationBTech = (EditText)findViewById(R.id.editTextCompensationBTech);
        editTextCompensationMTech = (EditText)findViewById(R.id.editTextCompensationMTech);
        editTextPrePlacementTalk = (EditText)findViewById(R.id.editTextPrePlacementTalk);
        editTextEventDate = (EditText)findViewById(R.id.editTextEventDate);
        editTextComments = (EditText)findViewById(R.id.editTextComments);

        buttonSaveChanges = (Button) findViewById(R.id.buttonSaveChanges);
        buttonSaveChanges.setOnClickListener(this);


        Intent i = getIntent();
        JID = i.getStringExtra("JID");

        fillJobDetails();

    }

    public void fillJobDetails()
    {

        jDatabase = FirebaseDatabase.getInstance().getReference("JobProfiles");
        jDatabase.orderByKey().equalTo(JID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{

                    JobProfile jobProfile = dataSnapshot.getChildren().iterator().next().getValue(JobProfile.class);

                    editTextCompanyName.setText(jobProfile.getCompanyName());
                    editTextDesignation.setText(jobProfile.getDesignation());
                    editTextLocation.setText(jobProfile.getLocation());
                    editTextCompensationBTech.setText(String.valueOf(jobProfile.getCompensationBTech()));
                    editTextCompensationMTech.setText(String.valueOf(jobProfile.getCompensationMTech()));
                    editTextPrePlacementTalk.setText(jobProfile.getPrePlacementTalk());
                    editTextEventDate.setText(jobProfile.getEventDate());
                    editTextComments.setText(jobProfile.getComments());

                }
                catch(Exception ex)
                {
                    Log.d("Error","Error");
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("READ_FAILED", "READ_FAILED");

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == buttonSaveChanges)
        {
            jDatabase = FirebaseDatabase.getInstance().getReference("JobProfiles");
            jobObject = jDatabase.child(JID);

            Map<String, Object> jobMap = new HashMap<String, Object>();

            jobMap.put("companyName", editTextCompanyName.getText().toString().trim());
            jobMap.put("designation", editTextDesignation.getText().toString().trim());
            jobMap.put("location", editTextLocation.getText().toString().trim());
            jobMap.put("compensationBTech", Integer.parseInt(editTextCompensationBTech.getText().toString().trim()));
            jobMap.put("compensationMTech", Integer.parseInt(editTextCompensationMTech.getText().toString().trim()));
            jobMap.put("prePlacementTalk", editTextPrePlacementTalk.getText().toString().trim());
            jobMap.put("eventDate", editTextEventDate.getText().toString().trim());
            jobMap.put("comments", editTextComments.getText().toString().trim());

            jobObject.updateChildren(jobMap);


            Intent i = new Intent(getApplicationContext(), SingleJobProfileDetails.class);
            i.putExtra("JID", JID);
            startActivity(i);

        }

    }

}
