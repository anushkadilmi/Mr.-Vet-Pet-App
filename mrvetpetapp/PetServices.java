package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PetServices extends AppCompatActivity {
    private CardView C20, C21, C22, C23 ,C24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_services);

        C20 = findViewById(R.id.card20);
        C21 = findViewById(R.id.card21);
        C22 = findViewById(R.id.card22);
        C23 = findViewById(R.id.card23);
        C24=findViewById(R.id.card24);

        C20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetBoarding();
            }
        });

        C21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetGrooming();
            }
        });
        C22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetTraining();
            }
        });
        C23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetSitting();
            }
        });

        C24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetAdopt();
            }
        });
    }

    public void openPetBoarding() {
        Intent intent = new Intent(this, PetBoarding.class);
        startActivity(intent);
    }

    public void openPetGrooming() {
        Intent intent = new Intent(this, PetGrooming.class);
        startActivity(intent);
    }

    public void openPetTraining() {
        Intent intent = new Intent(this, PetTraining.class);
        startActivity(intent);
    }

    public void openPetSitting() {
        Intent intent = new Intent(this, PetSitting.class);
        startActivity(intent);
    }

    public void openPetAdopt() {
        Intent intent = new Intent(this, PetAdopt.class);
        startActivity(intent);
    }
}
