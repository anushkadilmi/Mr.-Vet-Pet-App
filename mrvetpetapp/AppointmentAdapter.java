package com.example.mrvetpetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AppointmentAdapter extends BaseAdapter {



        private Context context;
    private List<com.example.mrvetpetapp.Appoinment> appointmentList;

        public AppointmentAdapter(Context context, List<com.example.mrvetpetapp.Appoinment> appointmentList) {
            this.context = context;
            this.appointmentList = appointmentList;
        }

        @Override
        public int getCount() {
            return appointmentList.size();
        }

        @Override
        public Object getItem(int position) {
            return appointmentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_appoinment, null);
            }

            // Get the appointment at the current position
           com.example.mrvetpetapp.Appoinment appoinment = appointmentList.get(position);

            // Display appointment details in TextViews
            TextView ownerNameTextView = view.findViewById(R.id.ownerNameTextView);
            TextView petNameTextView = view.findViewById(R.id.petNameTextView);
            TextView dateTextView = view.findViewById(R.id.dateTextView);
            TextView timeTextView = view.findViewById(R.id.timeTextView);
            TextView contactTextView = view.findViewById(R.id.contactTextView);

            ownerNameTextView.setText("Owner Name: " + appoinment.getOwnerName());
            petNameTextView.setText("Pet Name: " + appoinment.getPetName());
            dateTextView.setText("Date: " + appoinment.getDate());
            timeTextView.setText("Time: " + appoinment.getTime());
            contactTextView.setText("Contact: " + appoinment.getContact());

            return view;
        }
    }
