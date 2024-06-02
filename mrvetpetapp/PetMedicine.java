package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class PetMedicine extends AppCompatActivity {

    private CardView C1;
    private CardView C2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_medicine);

        C1=(CardView)findViewById(R.id.card1);
        C2=(CardView)findViewById(R.id.card1);


        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openlink();
            }
        });

        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openlink2();
            }
        });
    }

    public void openlink(){
        gotoUrl("https://www.uniquepharmacy.lk/product/vetzyme-b-plus-e-for-dogs-and-puppies-200-tablets/");
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openlink2(){
        gotoUrl2("https://petshopper.lk/products/frontline-spot-on-dog");
    }

    private void gotoUrl2(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}