package com.example.mrvetpetapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class appdetails extends AppCompatActivity {
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appdetails);

        ImageButton button =  findViewById(R.id.btn13);

        Intent intent = getIntent();
        if (intent != null) {
            String ownerName = intent.getStringExtra("ownerName");
            String petName = intent.getStringExtra("petName");
            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");

            // Get other appointment details in a similar way

            // Display appointment details in TextViews
            TextView txtOwnerName = findViewById(R.id.txtOwnerName);
            txtOwnerName.setText("Owner Name: " + ownerName);

            TextView txtPetName = findViewById(R.id.txtPetName);
            txtPetName.setText("Pet Name: " + petName);

            TextView txtDate = findViewById(R.id.txtDate);
            txtDate.setText("Date: " + date);

            TextView txtTime = findViewById(R.id.txtTime);
            txtTime.setText("Time: " + time);
        }

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openHomepg();
            }
        });
    }
    public void openHomepg(){
        Intent intent=new Intent(this, HomePg.class);
        startActivity(intent);
    }
}

