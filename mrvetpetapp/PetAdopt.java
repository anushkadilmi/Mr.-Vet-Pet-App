package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PetAdopt extends AppCompatActivity {

    private CardView C30;
    private CardView C31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petadopt);

        C30 = findViewById(R.id.card30);
        C31 = findViewById(R.id.card31);

        C30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink();
            }
        });
        C31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdopt();
            }
        });
    }

    public void openLink() {
        gotoUrl("https://www.awpasl.org/dehiwela-shelter/");
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void openAdopt() {
        Intent intent = new Intent(this, Adopt.class);
        startActivity(intent);
    }
}