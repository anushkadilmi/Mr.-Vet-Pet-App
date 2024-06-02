package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePg extends AppCompatActivity {

    private CardView C1,C2,C3,C4,C5,C6,C7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pg);

        C1=(CardView)findViewById(R.id.card1);
        C2=(CardView)findViewById(R.id.card2);
        C3=(CardView)findViewById(R.id.card3);
        C4=(CardView)findViewById(R.id.card4);
        C5=(CardView)findViewById(R.id.card5);
        C7=(CardView)findViewById(R.id.card7);


        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openMyPets();
            }
        });

        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openAddAppointment();
            }
        });
        C3.setOnClickListener(new View.OnClickListener(){
           @Override
            public  void onClick(View v){openMyAppointmentss();
            }
        });
        C4.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openMedicalHistory();
            }
        });
        C5.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetServices();
            }
        });
        C7.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetStore();
            }
        });
    }

    public void openMyPets(){
        Intent intent=new Intent(this, AddNewPet.class);
        startActivity(intent);
    }

    public void openAddAppointment(){

        Intent intent=new Intent(this, AddAppointment.class);
        startActivity(intent);
    }

    public void openMyAppointmentss(){

       Intent intent=new Intent(this, MyAppoinmentss.class);
        startActivity(intent);
    }
    public void openMedicalHistory(){
        Intent intent=new Intent(this, MedicalHistory.class);
        startActivity(intent);
    }

    public void openPetServices(){
        Intent intent=new Intent(this, PetServices.class);
        startActivity(intent);
    }

    public void openPetStore(){
        Intent intent=new Intent(this, PetStore.class);
        startActivity(intent);
    }
}