package com.example.saloni.placementv2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class JobProfileAdapter extends RecyclerView.Adapter<JobProfileAdapter.JobProfileHolder>{




    ArrayList<JobProfile> jobProfiles = null;


    public JobProfileAdapter(ArrayList<JobProfile> jobProfiles) {
        this.jobProfiles = jobProfiles;
    }

    public JobProfileHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_profile_layout, parent, false);
        return new JobProfileHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobProfileHolder holder, int position)
    {
        JobProfile jobProfile = jobProfiles.get(position);

        holder.textViewID.setText(jobProfile.getID());
        holder.textViewCompanyName.setText(jobProfile.getCompanyName());
        holder.textViewDesignation.setText(jobProfile.getDesignation());
        holder.textViewLocation.setText(jobProfile.getLocation());
        holder.textViewCompensationBTech.setText(String.valueOf(jobProfile.getCompensationBTech()));
        holder.textViewCompensationMTech.setText(String.valueOf(jobProfile.getCompensationMTech()));
        holder.textViewPrePlacementTalk.setText(jobProfile.getPrePlacementTalk());
        holder.textViewEventDate.setText(jobProfile.getEventDate());
        holder.textViewComments.setText(jobProfile.getComments());

    }

    @Override
    public int getItemCount()
    {
        return jobProfiles.size();
    }


    static class JobProfileHolder extends RecyclerView.ViewHolder
    {
        TextView textViewID;
        TextView textViewCompanyName;
        TextView textViewDesignation;
        TextView textViewLocation;
        TextView textViewCompensationBTech;
        TextView textViewCompensationMTech;
        TextView textViewPrePlacementTalk;
        TextView textViewEventDate;
        TextView textViewComments;



        public JobProfileHolder(View view)
        {
            super(view);

            textViewID = (TextView)view.findViewById(R.id.textViewID);
            textViewCompanyName = (TextView)view.findViewById(R.id.textViewCompanyName);
            textViewDesignation = (TextView)view.findViewById(R.id.textViewDesignation);
            textViewLocation = (TextView)view.findViewById(R.id.textViewLocation);
            textViewCompensationBTech = (TextView)view.findViewById(R.id.textViewCompensationBTech);
            textViewCompensationMTech = (TextView)view.findViewById(R.id.textViewCompensationMTech);
            textViewPrePlacementTalk = (TextView)view.findViewById(R.id.textViewPrePlacementTalk);
            textViewEventDate = (TextView)view.findViewById(R.id.textViewEventDate);
            textViewComments = (TextView)view.findViewById(R.id.textViewComments);


        }

    }


}

