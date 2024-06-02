package com.example.mrvetpetapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrvetpetapp.MyPets;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNewPet extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ImageView profileImageView;
    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pets"); // "pets" is the name of your Firebase database node

        profileImageView = findViewById(R.id.profile_image);

        EditText dobEditText = findViewById(R.id.time);
        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(dobEditText);
            }
        });

        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        Button chooseImageButton = findViewById(R.id.choose_image_button);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });
    }

    private void showDatePickerDialog(final EditText dobEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewPet.this,
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    // Set the selected date to the Date of Birth EditText
                    dobEditText.setText(dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1);
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }

    private void addPetToFirebase(String name, String type, String color, String breed, String dob, String weight) {
        // Create a HashMap to store pet details
        Map<String, Object> petMap = new HashMap<>();
        petMap.put("name", name);
        petMap.put("type", type);
        petMap.put("colour", color); // Use "colour" instead of "color"
        petMap.put("breed", breed);
        petMap.put("dob", dob);
        petMap.put("weight", weight);
        if (imageUri != null) {
            petMap.put("profileImageUrl", imageUri.toString());
        }

        // Push the new pet data to the Firebase database
        databaseReference.push().setValue(petMap);

        // Pass data back to MyPets activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("type", type);
        resultIntent.putExtra("colour", color);
        resultIntent.putExtra("breed", breed);
        resultIntent.putExtra("dob", dob);
        resultIntent.putExtra("weight", weight);
        setResult(RESULT_OK, resultIntent);
        finish(); // Close the AddNewPet activity
    }

    private void onAddButtonClicked() {
        // Retrieve other pet details
        EditText nameEditText = findViewById(R.id.oname);
        EditText typeEditText = findViewById(R.id.pname);
        EditText colorEditText = findViewById(R.id.comments);
        EditText breedEditText = findViewById(R.id.date);
        EditText dobEditText = findViewById(R.id.time);
        EditText weightEditText = findViewById(R.id.no);

        String name = nameEditText.getText().toString();
        String type = typeEditText.getText().toString();
        String color = colorEditText.getText().toString();
        String breed = breedEditText.getText().toString();
        String dob = dobEditText.getText().toString();
        String weight = weightEditText.getText().toString();

        // Perform basic validation
        if (name.isEmpty() || type.isEmpty() || color.isEmpty() || breed.isEmpty() || dob.isEmpty() || weight.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return; // Stop the process if any field is empty
        }

        // Add the pet data to Firebase
        addPetToFirebase(name, type, color, breed, dob, weight);

        // Pass data back to MyPets activity
        Intent resultIntent = new Intent(AddNewPet.this, MyPets.class);
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("type", type);
        resultIntent.putExtra("colour", color); // Use "colour" instead of "color"
        resultIntent.putExtra("breed", breed);
        resultIntent.putExtra("dob", dob);
        resultIntent.putExtra("weight", weight);
        if (imageUri != null) {
            resultIntent.putExtra("profileImageUrl", imageUri.toString());
        }

        setResult(RESULT_OK, resultIntent);

        // Optionally, you can show a success message or navigate to another activity
        Toast.makeText(this, "Pet added successfully", Toast.LENGTH_SHORT).show();
        startActivity(resultIntent);
        finish(); // Close the AddNewPet activity
    }
}
