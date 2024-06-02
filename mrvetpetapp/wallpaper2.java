package com.example.mrvetpetapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;



public class wallpaper2 extends AppCompatActivity {
        private ImageButton button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wallpaper2);

           ImageButton button =  findViewById(R.id.btn13);

            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    openmain();;
                }
            });
        }
        public void openmain(){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

