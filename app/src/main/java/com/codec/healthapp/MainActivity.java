package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import Utils.MyAppUrl;
import calculators.BMICalculatorActivity;
import calculators.BodyFatCalculatorActivity;
import calculators.WHRCalculatorActivity;
import calculators.calorieCalculatorActivity;
import webView.WebViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void HomeRemediesClickListiner(View view){
        Intent obj = new Intent(MainActivity.this,Home_Remedies_Pro.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(obj);
    }

    public void RichFoodClickListiner(View view){
        Intent obj = new Intent(MainActivity.this,Rich_Food.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(obj);
    }
    public void BodyPartWiseClickListiner(View view){
        Intent obj = new Intent(MainActivity.this,Best_Food_Body_Wise.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(obj);
    }

    public void HealthBenefitsClickListiner(View view){

        Intent obj = new Intent(MainActivity.this,Health_Benefits_Activity.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(obj);
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

    //daily health hack click
    public void GoToWebViewActivity(View view){

        Intent intent = new Intent(this, WebViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        if(view.getId() == R.id.daily_hacks_button){
            intent.putExtra("url", MyAppUrl.DAILY_HEALTH_HACK_URL);
        }

        else if(view.getId() == R.id.question_button){
            intent.putExtra("url", MyAppUrl.QUESTION_URL);
        }

        startActivity(intent);

    }
}
