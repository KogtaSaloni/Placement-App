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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SingleJobProfileDetails extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference jDatabase;
    private DatabaseReference uDatabase;

    TextView textViewCompanyName;
    TextView textViewDesignation;
    TextView textViewLocation;
    TextView textViewCompensationBTech;
    TextView textViewCompensationMTech;
    TextView textViewPrePlacementTalk;
    TextView textViewEventDate;
    TextView textViewComments;

    Button buttonEdit;
    Button buttonApply;

    String JID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_job_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewCompanyName = (TextView)findViewById(R.id.textViewCompanyName);
        textViewDesignation = (TextView)findViewById(R.id.textViewDesignation);
        textViewLocation = (TextView)findViewById(R.id.textViewLocation);
        textViewCompensationBTech = (TextView)findViewById(R.id.textViewCompensationBTech);
        textViewCompensationMTech = (TextView)findViewById(R.id.textViewCompensationMTech);
        textViewPrePlacementTalk = (TextView)findViewById(R.id.textViewPrePlacementTalk);
        textViewEventDate = (TextView)findViewById(R.id.textViewEventDate);
        textViewComments = (TextView)findViewById(R.id.textViewComments);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currUID = user.getUid();
        Log.d("currUser",currUID);


        //check if this user is admin or student

        uDatabase = FirebaseDatabase.getInstance().getReference("Users");
        uDatabase.orderByChild("uid").equalTo(currUID).addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                try{

                    User currUser = dataSnapshot.getChildren().iterator().next().getValue(User.class);
                    Log.d("currUser",currUser.getName());
                    Log.d("currUser","check");
                    Log.d("currUser",currUser.getRole());


                    if(currUser.getRole().equals("admin"))
                    {
                        Log.d("currUser","reaches here");

                        buttonEdit = (Button)findViewById(R.id.buttonEdit);
                        buttonEdit.setVisibility(View.VISIBLE);
                        buttonEdit.setOnClickListener(SingleJobProfileDetails.this);

                    }

                    else
                    {

                        buttonApply = (Button)findViewById(R.id.buttonApply);
                        buttonApply.setOnClickListener(SingleJobProfileDetails.this);
                        buttonApply.setVisibility(View.VISIBLE);
                    }

                }
                catch(Exception ex)
                {
                    Log.d("FirebaseError",ex.getMessage());
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("READ_FAILED", "READ_FAILED");

            }


        });

        buttonEdit = (Button)findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(this);

        Intent i = getIntent();
        JID = i.getStringExtra("JID");

        displayJob(JID);
    }

    public void displayJob(String JID)
    {

        jDatabase = FirebaseDatabase.getInstance().getReference("JobProfiles");
        jDatabase.orderByKey().equalTo(JID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{

                    JobProfile jobProfile = dataSnapshot.getChildren().iterator().next().getValue(JobProfile.class);

                    textViewCompanyName.setText(jobProfile.getCompanyName());
                    textViewDesignation.setText(jobProfile.getDesignation());
                    textViewLocation.setText(jobProfile.getLocation());
                    textViewCompensationBTech.setText(String.valueOf(jobProfile.getCompensationBTech()));
                    textViewCompensationMTech.setText(String.valueOf(jobProfile.getCompensationMTech()));
                    textViewPrePlacementTalk.setText(jobProfile.getPrePlacementTalk());
                    textViewEventDate.setText(jobProfile.getEventDate());
                    textViewComments.setText(jobProfile.getComments());

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

        if(view == buttonEdit)

        {

            Intent myIntent = new Intent(SingleJobProfileDetails.this, EditJobProfile.class);
            myIntent.putExtra("JID", JID);
            SingleJobProfileDetails.this.startActivity(myIntent);

        }

        if(view == buttonApply)
        {
            Toast.makeText(this, "Applied", Toast.LENGTH_SHORT).show();


        }

    }

}
