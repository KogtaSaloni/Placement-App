package com.example.saloni.placementv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetJobProfiles extends AppCompatActivity {

    private DatabaseReference jDatabase;
    private ListView listViewAllJobs;
    private RecyclerView recyclerView;
    private JobProfileAdapter jobProfileAdapter;
    private ArrayList<JobProfile> jobProfiles = new ArrayList<JobProfile>();
    private FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_job_profiles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);

        getAllJobDetails();

    }

    public void displayData() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        jobProfileAdapter = new JobProfileAdapter(jobProfiles);

        RecyclerView.LayoutManager jobProfileLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(jobProfileLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(jobProfileAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    public void onItemClick(View view, int position) {

                        JobProfile jobProfile = jobProfiles.get(position);
                        String JID = jobProfile.getID();

                        Intent i = new Intent(getApplicationContext(), SingleJobProfileDetails.class);
                        i.putExtra("JID", JID);
                        startActivity(i);

                    }
                }
                )
        );

    }


    public void getAllJobDetails() {
        jDatabase = FirebaseDatabase.getInstance().getReference("JobProfiles");
        Log.d("getshere", "reacheshere");


        jDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot allDataSnapshot) {
                Log.d("reaches", "reacheshere");

                for (DataSnapshot dataSnapshot : allDataSnapshot.getChildren()) {
                    JobProfile profile = dataSnapshot.getValue(JobProfile.class);
                    Log.d("CompanyName", profile.getCompanyName());
                    jobProfiles.add(profile);
                }

                displayData();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("READ_FAILED", "READ_FAILED");

            }
        });


    }


}
