package com.example.mrvetpetapp;

public class BookAppoinments {


        private String ownerName;
        private String petName;
        private String date;
        private String time;
        private String contact;

        // Default constructor (required for Firebase)
        public BookAppoinments() {
        }

        public BookAppoinments(String ownerName, String petName, String date, String time, String contact) {
            this.ownerName = ownerName;
            this.petName = petName;
            this.date = date;
            this.time = time;
            this.contact = contact;
        }

        // Getters and setters
        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }

