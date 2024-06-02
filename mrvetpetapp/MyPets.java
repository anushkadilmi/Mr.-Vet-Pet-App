package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPets extends AppCompatActivity {

    private ImageButton button;
    private ArrayList<HashMap<String, String>> petList; // List to store pet information
    private LinearLayout petContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        button = findViewById(R.id.btn1);
        petContainer = findViewById(R.id.petContainer);
        petList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPetForm();
            }
        });

        // Retrieve the added pet information from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            HashMap<String, String> addedPet = new HashMap<>();
            addedPet.put("name", intent.getStringExtra("name"));
            addedPet.put("type", intent.getStringExtra("type"));
            addedPet.put("colour", intent.getStringExtra("colour"));
            addedPet.put("breed", intent.getStringExtra("breed"));
            addedPet.put("dob", intent.getStringExtra("dob"));
            addedPet.put("weight", intent.getStringExtra("weight"));
            if (intent.hasExtra("profileImageUrl")) {
                addedPet.put("profileImageUrl", intent.getStringExtra("profileImageUrl"));
            }
            // Add the new pet to the list
            petList.add(addedPet);

            // Display pet information
            displayPetReminders();
        }
    }

    private void displayPetReminders() {
        // Display pet information in TextViews
        for (int i = 0; i < petList.size(); i++) {
            String reminderMessage = "Pet " + (i + 1) + " details:\n" +
                    "Name: " + petList.get(i).get("name") + "\n" +
                    "Type: " + petList.get(i).get("type") + "\n" +
                    "Colour: " + petList.get(i).get("colour") + "\n" +
                    "Breed: " + petList.get(i).get("breed") + "\n" +
                    "DOB: " + petList.get(i).get("dob") + "\n" +
                    "Weight: " + petList.get(i).get("weight");


            // Create a new TextView for each pet and add it to the petContainer
            TextView textViewDetails = new TextView(this);
            textViewDetails.setText(reminderMessage);




            petContainer.addView(textViewDetails);
        }
    }

    public void openAddPetForm() {
        Intent intent = new Intent(this, AddNewPet.class);
        intent.putExtra("petList", petList); // Pass the existing petList to AddNewPet
        startActivityForResult(intent, 1); // Use startActivityForResult to get result from AddNewPet
    }

    // Handle the result from AddNewPet
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Retrieve the updated petList from AddNewPet
            petList = (ArrayList<HashMap<String, String>>) data.getSerializableExtra("petList");
            // Clear the petContainer before displaying updated pet information
            petContainer.removeAllViews();
            displayPetReminders();
        }
    }
}
