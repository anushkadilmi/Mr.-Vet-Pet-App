package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAppoinments extends AppCompatActivity {

    private ImageButton button;
    private ArrayList<HashMap<String, String>> appointmentList; // List to store appointment information
    private LinearLayout appointmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appoinments);

        button = findViewById(R.id.btn1);
        appointmentContainer = findViewById(R.id.appointmentContainer);
        appointmentList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddAppointmentForm();
            }
        });

        // Retrieve the added appointment information from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ownerName")) {
            HashMap<String, String> addedAppointment = new HashMap<>();
            addedAppointment.put("ownerName", intent.getStringExtra("ownerName"));
            addedAppointment.put("petName", intent.getStringExtra("petName"));
            addedAppointment.put("date", intent.getStringExtra("date"));
            addedAppointment.put("time", intent.getStringExtra("time"));
            addedAppointment.put("contact", intent.getStringExtra("contact"));

            // Add the new appointment to the list
            appointmentList.add(addedAppointment);

            // Display appointment information
            displayAppointments();
        }
    }

    private void displayAppointments() {
        // Display appointment information in TextViews
        for (int i = 0; i < appointmentList.size(); i++) {
            String appointmentDetails = "Appointment " + (i + 1) + " details:\n" +
                    "Owner Name: " + appointmentList.get(i).get("ownerName") + "\n" +
                    "Pet Name: " + appointmentList.get(i).get("petName") + "\n" +
                    "Date: " + appointmentList.get(i).get("date") + "\n" +
                    "Time: " + appointmentList.get(i).get("time") + "\n" +
                    "Contact: " + appointmentList.get(i).get("contact");

            // Create a new TextView for each appointment and add it to the appointmentContainer
            TextView textViewDetails = new TextView(this);
            textViewDetails.setText(appointmentDetails);
            appointmentContainer.addView(textViewDetails);
        }
    }

    public void openAddAppointmentForm() {
        Intent intent = new Intent(this, AddAppointment.class);
        startActivity(intent);
    }
}
