package com.example.mrvetpetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Onboarding extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        layoutOnboardingIndicators=findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction=findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager =  findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(onboardingViewPager.getCurrentItem()+1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }else{
                    startActivity(new Intent(getApplicationContext(),HomePg.class));
                    finish();
                }
            }

        });
    }

    private void setupOnboardingItems(){
        List<OnboardingItem>onboardingItems=new ArrayList<>();
        OnboardingItem itemPayOnline =new OnboardingItem();
        itemPayOnline.setTitle("Pay your bill online");
        itemPayOnline.setDescription("Electric bill payment is a feature of online,mobile and telephone banking.");
        itemPayOnline.setImage(R.drawable.t);

        OnboardingItem itemOnTheWay =  new OnboardingItem();
        itemOnTheWay.setTitle("Your Food is on the way");
        itemOnTheWay.setDescription("bhb bjbkjgb vbdhjbdj vbdhjfjd vbfdjdj");
        itemOnTheWay.setImage(R.drawable.b22);

        OnboardingItem itemEatTogether =  new OnboardingItem();
        itemEatTogether.setTitle("Eat together");
        itemEatTogether.setDescription("bhb bjbkjgb vbdhjbdj vbdhjfjd vbfdjdj");
        itemEatTogether.setImage(R.drawable.picc23);

        onboardingItems.add(itemPayOnline);
        onboardingItems.add(itemOnTheWay);
        onboardingItems.add(itemEatTogether);

        onboardingAdapter=new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators=new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
            indicators[i]=new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicators(int index){
        int childCount=layoutOnboardingIndicators.getChildCount();
        for(int i=0;i<childCount;i++){
            ImageView imageView=(ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }
            else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index==onboardingAdapter.getItemCount()-1){
            buttonOnboardingAction.setText("Start");
        }
        else{
            buttonOnboardingAction.setText("Next");
        }
    }

}