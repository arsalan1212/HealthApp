package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by RNSolution on 11/10/2017.
 */

public class Best_Food_Body_Wise extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle createBundle)
    {
        super.onCreate(createBundle);
        setContentView(R.layout.best_food_body_wise);


    }

    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }
}
