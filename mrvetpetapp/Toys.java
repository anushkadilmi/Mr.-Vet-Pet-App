package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Toys extends AppCompatActivity {
    private CardView C1;
    private CardView C2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys);

        C1=(CardView)findViewById(R.id.card1);
        C2=(CardView)findViewById(R.id.card1);


        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openlink1();
            }
        });

        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openlink1();
            }
        });
    }

    public void openlink1(){
        gotoUrl("https://petmart.lk/collections/dog-toys/products/trixie-rope-dumbbell");
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openlink2(){
        gotoUrl2("https://petmart.lk/collections/dog-toys/products/jumper-on-a-rope");
    }

    private void gotoUrl2(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}