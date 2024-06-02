package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PetStore extends AppCompatActivity {

    private CardView C1,C2,C3,C4,C5,C6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_store);

        C1=(CardView)findViewById(R.id.card1);
        C2=(CardView)findViewById(R.id.card2);
        C3=(CardView)findViewById(R.id.card3);
        C4=(CardView)findViewById(R.id.card4);
        C5=(CardView)findViewById(R.id.card5);
        C6=(CardView)findViewById(R.id.card6);


        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetFood();
            }
        });

        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetVaccine();
            }
        });
        C3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetMedicine();
            }
        });
        C4.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetToys();
            }
        });
        C5.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetEssentials();
            }
        });
        C6.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){openPetGroominProducts();
            }
        });
    }

    public void openPetFood(){
        Intent intent=new Intent(this, PetFood.class);
        startActivity(intent);
    }
    public void openPetVaccine(){
        Intent intent=new Intent(this, PetVaccine.class);
        startActivity(intent);
    }
    public void openPetMedicine(){
        Intent intent=new Intent(this, PetMedicine.class);
        startActivity(intent);
    }
    public void openPetToys(){
        Intent intent=new Intent(this, Toys.class);
        startActivity(intent);
    }
    public void openPetEssentials(){
        Intent intent=new Intent(this, Essentials.class);
        startActivity(intent);
    }
    public void openPetGroominProducts(){
        Intent intent=new Intent(this, Grooming.class);
        startActivity(intent);
    }
}