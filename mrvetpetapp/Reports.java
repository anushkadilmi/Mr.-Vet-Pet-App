package com.example.mrvetpetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mrvetpetapp.ViewPDFActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Reports extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        Button btnUploadReport = findViewById(R.id.btnUploadReport);
        btnUploadReport.setOnClickListener(view -> openFileChooser());
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf"); // Filter to only show PDF files
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a PDF file"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle exception
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFileToStorage(Uri fileUri) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = getContentResolver().openInputStream(fileUri);
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File outputFile = new File(storageDir, "report.pdf");
            outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            Toast.makeText(this, "File saved successfully", Toast.LENGTH_SHORT).show();
            openPdf(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openPdf(File file) {
        Intent intent = new Intent(Reports.this, ViewPDFActivity.class);
        intent.putExtra("pdfPath", file.getAbsolutePath());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            saveFileToStorage(uri);
        }
    }
}
