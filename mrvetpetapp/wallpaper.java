package com.example.mrvetpetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class wallpaper extends AppCompatActivity {

        private ImageButton button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wallpaper);

            ImageButton button =  findViewById(R.id.btn13);

            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){openwallpaper2();
                }
            });
        }
        public void openwallpaper2(){
            Intent intent=new Intent(this,wallpaper2.class);
            startActivity(intent);
        }
    }

