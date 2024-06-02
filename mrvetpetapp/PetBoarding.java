package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PetBoarding extends AppCompatActivity {

    private CardView C10;
    private CardView C17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petboarding);

        C10 = findViewById(R.id.card10);
        C17 = findViewById(R.id.card17);

        C10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBoaders();
            }
        });

        C17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink2();
            }
        });
    }

    public void openLink2() {
        gotoUrl2("https://lankainformation.lk/directory/house-holds/pet-care-and-boarding-services/36228-aranyani-animal-rescue-haven-dog-hotel");
    }

    private void gotoUrl2(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }



    public void openBoaders() {
        Intent intent = new Intent(this,Boarders.class);
        startActivity(intent);
    }

}

