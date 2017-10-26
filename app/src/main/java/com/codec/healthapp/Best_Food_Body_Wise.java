package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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


    public void bodyPartsClickListiner(View view){

        int id = view.getId();

        switch (id){

            case R.id.brain_:
                Toast.makeText(this, "Brain", Toast.LENGTH_SHORT).show();
                break;

            case R.id.teeth_:
                Toast.makeText(this, "Teeth", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lungs_:
                Toast.makeText(this, "Lungs", Toast.LENGTH_SHORT).show();
                break;

            case R.id.liver_:
                Toast.makeText(this, "Liver", Toast.LENGTH_SHORT).show();
                break;

            case R.id.vines_:
                Toast.makeText(this, "Vines", Toast.LENGTH_SHORT).show();
                break;

            case R.id.bones_:
                Toast.makeText(this, "Bones", Toast.LENGTH_SHORT).show();
                break;

            case R.id.eye_:
                Toast.makeText(this, "Eye", Toast.LENGTH_SHORT).show();
                break;

            case R.id.heart_:
                Toast.makeText(this, "Heart", Toast.LENGTH_SHORT).show();
                break;

            case R.id.kidney_:
                Toast.makeText(this, "Kidney", Toast.LENGTH_SHORT).show();
                break;

            case R.id.digestive_system_:
                Toast.makeText(this, "Digestive System", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nail_:
                Toast.makeText(this, "Nail", Toast.LENGTH_SHORT).show();
                break;

        }

    }
    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }
}
