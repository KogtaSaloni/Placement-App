package com.example.saloni.placementv2;

import java.util.ArrayList;
import java.util.List;

public class JobProfile {

    private String ID;
    private String companyName;
    private String designation;
    private String location;
    private int compensationBTech;
    private int compensationMTech;
    private String prePlacementTalk;
    private String eventDate;
    private String comments;

    public JobProfile()
    {
        this.companyName = null;
        this.designation = null;
        this.location = null;
        this.compensationBTech = 0;
        this.compensationMTech = 0;
        this.prePlacementTalk = null;
        this.eventDate = null;
        this.comments = null;
    }


    public JobProfile(String ID, String companyName, String designation, String location,
                      int compensationBtech, int compensationMtech, String prePlacementTalk,
                      String eventDate, String comments) {

        this.ID = ID;
        this.companyName = companyName;
        this.designation = designation;
        this.location = location;
        this.compensationBTech = compensationBtech;
        this.compensationMTech = compensationMtech;
        this.prePlacementTalk = prePlacementTalk;
        this.eventDate = eventDate;
        this.comments = comments;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCompensationBTech() {
        return compensationBTech;
    }

    public void setCompensationBTech(int compensationBTech) {
        this.compensationBTech = compensationBTech;
    }

    public long getCompensationMTech() {
        return compensationMTech;
    }

    public void setCompensationMTech(int compensationMTech) {
        this.compensationMTech = compensationMTech;
    }

    public String getPrePlacementTalk() {
        return prePlacementTalk;
    }

    public void setPrePlacementTalk(String prePlacementTalk) {
        this.prePlacementTalk = prePlacementTalk;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



}
