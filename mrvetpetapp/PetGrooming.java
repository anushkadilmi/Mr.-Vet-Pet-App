package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PetGrooming extends AppCompatActivity {

    private CardView C11;
    private CardView C18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petgrooming);

        C11 = findViewById(R.id.card11);
        C18 = findViewById(R.id.card18);

        C11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGroomings();
            }
        });
        C18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink2();
            }
        });
    }

    private void openGroomings() {
        Intent intent = new Intent(this, Groomings.class);
        startActivity(intent);
    }
    public void openLink2() {
        gotoUrl2("https://puppyplaytime.ca/?gclid=Cj0KCQiA5fetBhC9ARIsAP1UMgHDYQ0UDOUVhgOXeprMuZbiyLEwJjjBBNBRnMUDbNCLmuq5_Ioj_nwaAsTmEALw_wcB");
    }

    private void gotoUrl2(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
