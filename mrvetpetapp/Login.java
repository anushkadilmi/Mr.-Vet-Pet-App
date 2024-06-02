package com.example.mrvetpetapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText editTextemail, editTextpwd1;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView, forgotPasswordLink;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),new1.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextemail = findViewById(R.id.email);
        editTextpwd1 = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginbutton);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.registerNow);
        forgotPasswordLink = findViewById(R.id.forgotPassword);

        // Click listener for the "Forgot Password" link
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                progressBar.setVisibility(View.VISIBLE);
                String email, password;

                email = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextpwd1.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility((View.GONE));
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successful.",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),HomePg.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
