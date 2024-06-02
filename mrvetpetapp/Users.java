package com.example.mrvetpetapp;


public class Users {

    String ownerName, petName, date, userName,time,contact;

    public Users() {
    }

    public Users(String ownerName, String petName, String date, String time,String contact) {
        this.ownerName = ownerName;
        this.petName = petName;
        this.date = date;
        this.time = time;
        this.contact = contact;

    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String firstName) {
        this.ownerName = firstName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String lastName) {
        this.petName = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String age) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContact() {return time;}

    public void setContact(String contact) {
        this.contact = contact;
    }
}