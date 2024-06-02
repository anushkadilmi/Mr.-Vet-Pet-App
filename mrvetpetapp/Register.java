package com.example.mrvetpetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText editTextemail, editTextpwd1;
    Button register;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        editTextemail = findViewById(R.id.edt_email);
        editTextpwd1 = findViewById(R.id.edt_pwd);
        register = findViewById(R.id.R_btn_1);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.loginNow);

        // Check if the user came from password reset
        if (getIntent().getBooleanExtra("fromPasswordReset", false)) {
            // Display a message or perform actions specific to password reset flow
            Toast.makeText(this, "Please set a new password.", Toast.LENGTH_SHORT).show();
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;

                email = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextpwd1.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // You can now use the 'email' and 'password' variables to create a new user
                // using Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    // Registration success
                                    Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    // You can navigate to the next activity or perform other actions here
                                } else {
                                    // If registration fails, display a message to the user.
                                    Toast.makeText(Register.this, "Registration failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                                    Log.e("Registration failed", String.valueOf(task.getException()));
                                }
                            }
                        });

            }
        });
    }
}