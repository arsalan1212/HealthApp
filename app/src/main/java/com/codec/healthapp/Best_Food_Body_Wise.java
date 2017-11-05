package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import Utils.BodyPartsArray;

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

    //disease click listiner
    public void DiseaseClickListiner(View view){
        Intent obj = new Intent(this,Home_Remedies_Pro.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(obj);
        finish();
    }

    //plant click listiner
    public void PlantClickListiner(View view){

        Intent obj = new Intent(this,Health_Benefits_Activity.class);
        obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(obj);
        finish();
    }

    public void bodyPartsClickListiner(View view){

        String titleArray[] =null;
        String descriptionArray[] =null;
        int imagesArray[] =null;
        String bodyPartName="";
        int id = view.getId();

        switch (id){

            case R.id.brain_:
                titleArray = getResources().getStringArray(R.array.brain_title);
                descriptionArray =getResources().getStringArray(R.array.brain_description);
                imagesArray = BodyPartsArray.brain_images;
                bodyPartName="Brain";
                break;

            case R.id.teeth_:
                titleArray = getResources().getStringArray(R.array.teeth_title);
                descriptionArray =getResources().getStringArray(R.array.teeth_description);
                imagesArray = BodyPartsArray.tooth_images;
                bodyPartName="Tooth";
                break;

            case R.id.lungs_:
                titleArray = getResources().getStringArray(R.array.lungs_title);
                descriptionArray =getResources().getStringArray(R.array.lungs_description);
                imagesArray = BodyPartsArray.lungs_images;
                bodyPartName="Lungs";
                break;

            case R.id.liver_:
                titleArray = getResources().getStringArray(R.array.liver_title);
                descriptionArray =getResources().getStringArray(R.array.liver_description);
                imagesArray = BodyPartsArray.liver_images;
                bodyPartName="Liver";
                break;

            case R.id.vines_:
                titleArray = getResources().getStringArray(R.array.vien_title);
                descriptionArray =getResources().getStringArray(R.array.vien_description);
                imagesArray = BodyPartsArray.vien_images;
                bodyPartName="Vines";
                break;

            case R.id.bones_:
                titleArray = getResources().getStringArray(R.array.bones_title);
                descriptionArray =getResources().getStringArray(R.array.bones_description);
                imagesArray = BodyPartsArray.bone_images;
                bodyPartName="Bones";

                break;

            case R.id.eye_:
                titleArray = getResources().getStringArray(R.array.eyes_title);
                descriptionArray =getResources().getStringArray(R.array.eyes_description);
                imagesArray = BodyPartsArray.eyes_images;
                bodyPartName="Eye";
                break;

            case R.id.heart_:
                titleArray = getResources().getStringArray(R.array.heart_title);
                descriptionArray =getResources().getStringArray(R.array.heart_description);
                imagesArray = BodyPartsArray.heart_images;
                bodyPartName="Heart";
                break;

            case R.id.kidney_:
                titleArray = getResources().getStringArray(R.array.kidney_title);
                descriptionArray =getResources().getStringArray(R.array.kidney_description);
                imagesArray = BodyPartsArray.kidney_images;
                bodyPartName="Kidney";
                break;

            case R.id.digestive_system_:
                titleArray = getResources().getStringArray(R.array.digestive_system_title);
                descriptionArray =getResources().getStringArray(R.array.digestive_system_description);
                imagesArray = BodyPartsArray.digestivesytem_images;
                bodyPartName="Digestive System";
                break;

            case R.id.nail_:
                titleArray = getResources().getStringArray(R.array.nail_title);
                descriptionArray =getResources().getStringArray(R.array.nail_description);
                imagesArray = BodyPartsArray.nail_images;
                bodyPartName="Nail";
                break;

        }
        Intent intent = new Intent(Best_Food_Body_Wise.this,Body_parts_Detail.class);
        intent.putExtra("titleArray",titleArray);
        intent.putExtra("descriptionArray",descriptionArray);
        intent.putExtra("imageArray",imagesArray);
        intent.putExtra("bodypartName", bodyPartName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }
}
