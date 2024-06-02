package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;  // Import AppCompatActivity instead of just Activity
import androidx.cardview.widget.CardView;

public class PetTraining extends AppCompatActivity {

    private CardView C12;
    private CardView C15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pettraining);

        C12 = findViewById(R.id.card12);
        C15 = findViewById(R.id.card15);

        C12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTraining();
            }
        });

        C15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink2();
            }
        });
    }

    private void openTraining() {
        Intent intent = new Intent(this, Training.class);
        startActivity(intent);
    }

    public void openLink2() {
        gotoUrl2("https://dogs.lk/adFullView/769/4");
    }

    private void gotoUrl2(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
