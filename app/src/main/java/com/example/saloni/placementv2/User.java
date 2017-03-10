package com.example.saloni.placementv2;


public class User {

    private String UID;
    private String email;
    private String name;
    private String rollNumber;
    private String program;
    private String stream;
    private String role;

    public User()
    {

        this.UID = null;
        this.email = null;
        this.name = null;
        this.rollNumber = null;
        this.program = null;
        this.stream = null;
        this.role = null;
    }

    public User(String UID, String email, String name, String rollNumber, String program, String stream, String role) {

        this.UID = UID;
        this.email = email;
        this.name = name;
        this.rollNumber = rollNumber;
        this.program = program;
        this.stream = stream;
        this.role = role;

    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

