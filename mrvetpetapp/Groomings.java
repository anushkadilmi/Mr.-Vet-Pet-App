package com.example.mrvetpetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Groomings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groomings);

        // Find the button by its ID
        Button addAppointmentButton = findViewById(R.id.addapppoinment);

        // Set click listener for the button
        addAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Groomings.this, AddAppointment.class);
                startActivity(intent);
            }
        });
    }
}
