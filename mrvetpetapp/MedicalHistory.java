package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicalHistory extends AppCompatActivity {

    private CardView C1;
    private CardView C2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        C1=(CardView)findViewById(R.id.cardh);
        C2=(CardView)findViewById(R.id.cardr);


        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openHistory();
            }
        });

        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openReports();
            }
        });

    }
    public void openHistory(){
        Intent intent=new Intent(this, MedicalHistory2.class);
        startActivity(intent);    }



    public void openReports(){
        Intent intent=new Intent(this, Reports.class);
        startActivity(intent);
    }
}