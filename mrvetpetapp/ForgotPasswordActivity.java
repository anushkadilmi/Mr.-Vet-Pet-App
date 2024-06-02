package com.example.mrvetpetapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonResetPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forogotpasswordactivity);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if (!TextUtils.isEmpty(email)) {
                    resetPassword(email);
                } else {
                    editTextEmail.setError("Enter your email");
                }
            }
        });
    }

    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Password reset email sent successfully
                            // Provide feedback to the user
                            Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent successfully", Toast.LENGTH_SHORT).show();
                            // For simplicity, just finish the activity in this example
                            finish();
                        } else {
                            // If the email address is not registered or other errors
                            // Provide feedback to the user
                            Toast.makeText(ForgotPasswordActivity.this, "Failed to send password reset email. Check your email address.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
