package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openLogin();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openRegister();
            }
        });
    }

    public void openLogin(){
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openRegister(){
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
    }
}