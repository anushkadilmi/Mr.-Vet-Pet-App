package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Essentials extends AppCompatActivity {
    private CardView C1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essentials);

        C1=(CardView)findViewById(R.id.card1);

        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openlink();
            }
        });
    }
    public void openlink(){
        gotoUrl("https://petessentials.lk/product/aluminium-bowl/");
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}