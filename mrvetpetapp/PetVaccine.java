package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class PetVaccine extends AppCompatActivity {

    private CardView C1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_vaccine);

        C1=(CardView)findViewById(R.id.card1);

        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openlink6();
            }
        });
    }

    public void openlink6(){
        gotoUrl("https://www.hayleysanimalhealth.lk/shop/pet-care/vaccines/hipradog-pv/");
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}