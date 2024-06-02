package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ViewPDFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String pdfPath = getIntent().getStringExtra("pdfPath");
        openPdf(pdfPath);
    }

    private void openPdf(String pdfPath) {
        File file = new File(pdfPath);
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "No PDF viewer installed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
