package com.example.mrvetpetapp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddAppointment extends AppCompatActivity {

    private EditText ownerNameEditText, petNameEditText, dateEditText, timeEditText, contactEditText;
    private Button addButton;
    private DatabaseReference databaseReference;
    private static final int PERMISSION_REQUEST_SEND_SMS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appoinment);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");

        ownerNameEditText = findViewById(R.id.ownerName);
        petNameEditText = findViewById(R.id.petName);
        dateEditText = findViewById(R.id.date);
        timeEditText = findViewById(R.id.time);
        contactEditText = findViewById(R.id.contact);

        // Set onClickListener for the date field
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set onClickListener for the time field
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAppointment();
            }
        });
    }

    // Method to show DatePickerDialog
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the date field with the selected date
                        dateEditText.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                },
                year, month, dayOfMonth);
        datePickerDialog.show();
    }

    // Method to show TimePickerDialog
    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Update the time field with the selected time
                        timeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                },
                hourOfDay, minute, true);
        timePickerDialog.show();
    }

    private void addAppointment() {
        if (validateInputs()) {
            // If inputs are valid, proceed with adding the appointment
            String ownerName = ownerNameEditText.getText().toString();
            String petName = petNameEditText.getText().toString();
            String date = dateEditText.getText().toString();
            String time = timeEditText.getText().toString();
            String contact = contactEditText.getText().toString();

            // Push the new appointment data to the Firebase database
            String key = databaseReference.push().getKey(); // Generate a unique key for the appointment
            com.example.mrvetpetapp.Appoinment appointment = new com.example.mrvetpetapp.Appoinment(ownerName, petName, date, time, contact);
            if (key != null) {
                databaseReference.child(key).setValue(appointment);
                showToast("Appointment added successfully!");
                // Pass appointment data to MyAppointments activity
                Intent intent = new Intent(AddAppointment.this, MyAppoinments.class);
                intent.putExtra("ownerName", ownerName);
                intent.putExtra("petName", petName);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("contact", contact);
                startActivity(intent);

                finish();
                // Send SMS
                sendSMS(contact);

                // Optionally, navigate to another activity or perform any other actions
            } else {
                showToast("Failed to add appointment");
            }
        }
    }

    private boolean validateInputs() {
        // Validate each input field
        if (TextUtils.isEmpty(ownerNameEditText.getText().toString())) {
            showToast("Enter owner's name");
            return false;
        }

        if (TextUtils.isEmpty(petNameEditText.getText().toString())) {
            showToast("Enter pet's name");
            return false;
        }

        if (TextUtils.isEmpty(dateEditText.getText().toString())) {
            showToast("Enter appointment date");
            return false;
        }

        if (TextUtils.isEmpty(timeEditText.getText().toString())) {
            showToast("Enter appointment time");
            return false;
        }

        if (TextUtils.isEmpty(contactEditText.getText().toString())) {
            showToast("Enter contact number");
            return false;
        }

        // Additional validations can be added as per your requirements

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void sendSMS(String contact) {
        String appName = "Mr.VET";
        String defaultMessage = "Hello from " + appName + ". Your appointment details:\n" +
                "Date: " + dateEditText.getText().toString() + "\n" +
                "Time: " + timeEditText.getText().toString() + "\n" +
                "Owner Name: " + ownerNameEditText.getText().toString() + "\n" +
                "Pet Name: " + petNameEditText.getText().toString() + "\n" +
                "Thank you!";

        try {
            if (checkPermission()) {
                android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                smsManager.sendTextMessage(contact, null, defaultMessage, null, null);
                Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, proceed with sending SMS
                sendSMS(contactEditText.getText().toString());
            } else {
                // Permission is denied, show a message or handle it accordingly
                Toast.makeText(this, "SEND_SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
