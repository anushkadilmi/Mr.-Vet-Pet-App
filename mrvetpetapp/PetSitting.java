package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;  // Import AppCompatActivity instead of just Activity
import androidx.cardview.widget.CardView;

public class PetSitting extends AppCompatActivity {

    private CardView C13;
    private CardView C16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petsitting);

        C13 = findViewById(R.id.card13);
        C16 = findViewById(R.id.card16);

        C13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink();
            }
        });

        C16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSitters();
            }
        });
    }

    public void openLink() {
        gotoUrl("https://www.petbacker.com/s/pet-house-sitting/sri-lanka");
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void openSitters() {
        Intent intent = new Intent(this, sitters.class);
        startActivity(intent);
    }

}
