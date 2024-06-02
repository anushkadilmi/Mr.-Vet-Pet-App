package com.example.mrvetpetapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAppoinmentss extends AppCompatActivity {

    private GridView gridView;
    private List<com.example.mrvetpetapp.Appoinment> appointmentList;
    private AppointmentAdapter adapter;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appoinmentss);

        gridView = findViewById(R.id.gridView);
        appointmentList = new ArrayList<>();
        adapter = new AppointmentAdapter(this, appointmentList);
        gridView.setAdapter(adapter);

        // Initialize Firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        reference = db.getReference("appointments");

        // Retrieve data from Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointmentList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                  com.example.mrvetpetapp.Appoinment appointment = snapshot.getValue(com.example.mrvetpetapp.Appoinment.class);
                    appointmentList.add(appointment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                // Handle errors
                Log.e("Firebase", "Error fetching data: " + databaseError.getMessage());
                Toast.makeText(MyAppoinmentss.this, "Error fetching data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
