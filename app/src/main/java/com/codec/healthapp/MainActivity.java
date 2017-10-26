package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import calculators.BMICalculatorActivity;
import calculators.BodyFatCalculatorActivity;
import calculators.WHRCalculatorActivity;
import calculators.calorieCalculatorActivity;

public class MainActivity extends AppCompatActivity {

    ImageView home_button,best_food_button,rich_food_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_button  = (ImageView) findViewById(R.id.home_button);
        best_food_button  = (ImageView) findViewById(R.id.best_food_button);
        rich_food_button  = (ImageView) findViewById(R.id.rich_food_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(MainActivity.this,Home_Remedies_Pro.class);
                startActivity(obj);
            }
        });
        best_food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(MainActivity.this,Best_Food_Body_Wise.class);
                startActivity(obj);
            }
        });
        rich_food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(MainActivity.this,Rich_Food.class);
                startActivity(obj);
            }
        });
    }


    //BMI Calculator
    public void BMICalculator(View view){

        Intent bmiIntent = new Intent(this, BMICalculatorActivity.class);
        bmiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(bmiIntent);
    }

    //calculate Calorie
    public void CalorieCalculator(View view){

        Intent calorieIntent = new Intent(this, calorieCalculatorActivity.class);
        calorieIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(calorieIntent);
    }

    //calculator of WHR
    public void WHRCalculator(View view){

        Intent whrIntent = new Intent(this, WHRCalculatorActivity.class);
        whrIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(whrIntent);
    }

    //Lauch calculator of body fat
    public void BodyFatCalculator(View view){

        Intent bodyFatIntent = new Intent(this, BodyFatCalculatorActivity.class);
        bodyFatIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(bodyFatIntent);
    }
}
