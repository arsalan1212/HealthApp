package calculators;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codec.healthapp.Health_Benefits_Activity;
import com.codec.healthapp.Home_Remedies_Pro;
import com.codec.healthapp.MainActivity;
import com.codec.healthapp.R;

public class BodyFatCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextWeight,editTextWaist;
    private TextView tvMaleBodyFat,tvFemaleBodyFat,tvBodyFatTitle;
    private ImageView imageViewMale,imageViewFemale;
    private LinearLayout layout_male ,layout_female,layout_disease,layout_plant;
    private String gender="";
    private Spinner mSpinnerWeight, mSpinnerWaist;
    private String[] fat_weight,fat_waist;
    private String spinnerWaist,spinnerWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_fat_calculator);

        //setting the Custom font
        tvBodyFatTitle = findViewById(R.id.tvBodyFatTitle);
        Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/sansation_light.ttf");
        tvBodyFatTitle.setTypeface(typeface);

        fat_weight = getResources().getStringArray(R.array.bodyfat_weight);
        fat_waist = getResources().getStringArray(R.array.bodyfat_waist);

        editTextWeight = findViewById(R.id.et_BodyFatWeight);
        editTextWaist = findViewById(R.id.et_BodyFatWaist);

        layout_male = findViewById(R.id.layout_male_BodyFat);
        layout_female = findViewById(R.id.layout_female_BodyFat);

        layout_disease = findViewById(R.id.layout_disease);
        layout_plant = findViewById(R.id.layout_plant);


        tvMaleBodyFat = findViewById(R.id.tvMaleBodyFat);
        tvFemaleBodyFat = findViewById(R.id.tvFemaleBodyFat);

        imageViewMale = findViewById(R.id.imageMaleAvatorBodyFat);
        imageViewFemale = findViewById(R.id.imageFemaleAvatorBodyFat);

        layout_male.setOnClickListener(this);
        layout_female.setOnClickListener(this);

        layout_disease.setOnClickListener(this);
        layout_plant.setOnClickListener(this);

        mSpinnerWeight = findViewById(R.id.spinner_weight_bodyFat);
        mSpinnerWaist = findViewById(R.id.spinner_waist_bodyFat);


        mSpinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                spinnerWeight = fat_weight[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerWaist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               spinnerWaist = fat_waist[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    //calculating the body fat
    public void calculateBodyFat(View view){


        String strWeight = editTextWeight.getText().toString().trim();
        String strWaist = editTextWaist.getText().toString().trim();


        if(!gender.equals("")){

            if( !TextUtils.isEmpty(strWaist) && !TextUtils.isEmpty(strWeight)){

                float waistValue = Float.parseFloat(strWaist);
                float weightValue = Float.parseFloat(strWeight);

                double weight=0,waist=0;
                if( waistValue >0  && weightValue >0){

                    if(spinnerWeight.equals("Kgs"))
                    {
                        weight =weightValue * 2.2046;
                    }
                    else if(spinnerWeight.equals("lbs")){
                        weight = weightValue;
                    }

                    if(spinnerWaist.equals("Cms")){
                        waist =waistValue * 0.39370;
                    }
                    else if(spinnerWaist.equals("Inches")){
                        waist = waistValue;
                    }
                    if(gender.equals("male")){

                        calculateMaleBodyFat(weight,waist);
                    }
                    else if(gender.equals("female")){

                        calculateFemaleBodyFat(weight,waist);
                    }
                }
                else{
                    Toast.makeText(this, "Please Real value for all the Fields", Toast.LENGTH_SHORT).show();
                }

            }
            else{

                Toast.makeText(this, "Fill all the Required Fields", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "First Select Your Gender", Toast.LENGTH_SHORT).show();
        }

    }

    //calculating the Male body Fat
    private void calculateMaleBodyFat( double weightValue, double waistValue)
    {
        String result="";
        double BF = -98.42 + (4.15 * waistValue) - (.082 * weightValue);
        double  BFPercent = BF / weightValue;

        BFPercent = BFPercent * 100;

        BFPercent = Math.round(BFPercent);

        if(BFPercent >= 2 && BFPercent <= 5){
            result ="Essential fat";
        }
        else if(BFPercent >=6 && BFPercent <=13){
            result ="Athletes";
        }
        else if(BFPercent >= 14 && BFPercent <=17){
            result ="Fitness";
        }
        else if(BFPercent >= 18 && BFPercent <= 24){
            result ="Average";
        }
        else if(BFPercent >= 25){
            result ="Overweight or Obese";
        }


        if(BFPercent >=2){

            ShowBodyFatDialog(result, BFPercent);
        }else{
            Toast.makeText(this, "Body Fat Percentage must be Greater" +
                    " than 2 and current Value is:"+BFPercent, Toast.LENGTH_SHORT).show();
        }

    }



    //calculating the Female body Fat
    private void calculateFemaleBodyFat( double weightValue, double waistValue) {

        String result="";
        double BF = -76.76 + (4.15 * waistValue) - (.082 * weightValue);;
        double  BFPercent = BF / weightValue;

        BFPercent = BFPercent * 100;

        BFPercent = Math.round(BFPercent);

        if(BFPercent >= 10 && BFPercent <= 13){
            result ="Essential fat";
        }
        else if(BFPercent >=14 && BFPercent <=20){
            result ="Athletes";
        }
        else if(BFPercent >= 21 && BFPercent <=24){
            result ="Fitness";
        }
        else if(BFPercent >= 25 && BFPercent <= 31){
            result ="Average";
        }
        else if(BFPercent >= 32){
            result ="Overweight or Obese";
        }

        if(BFPercent >=10){

            ShowBodyFatDialog(result,BFPercent);
        }
        else{
            Toast.makeText(this, "Body Fat Percentage must be Greater" +
                    " than 10 and current Value is:"+BFPercent, Toast.LENGTH_SHORT).show();
        }


    }

    //Showing the Body Fat dialog
    private void ShowBodyFatDialog(String result, double BFPercent) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.result_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(false);

        TextView tvWHRResult = dialog.findViewById(R.id.textViewResult);
        TextView tvResultHeading = dialog.findViewById(R.id.textViewResultHeading);
        TextView tvResultRange = dialog.findViewById(R.id.textViewResultRange);
        ImageView imageViewClose = dialog.findViewById(R.id.imageView_dialog_close);


        tvWHRResult.setText("Your Body Fat is:\n"+BFPercent +" %");

        tvResultHeading.setText("It suggest that Your Body Fat Type is:");

        tvResultRange.setText(result);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    //back button
    public void GoBackbtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

        Intent intent =null;
        if(view.getId() == R.id.layout_male_BodyFat){
            gender ="male";

            tvMaleBodyFat.setTextColor(Color.WHITE);
            imageViewMale.setImageResource(R.drawable.man_avatar_select);

            //changing female text and image
            tvFemaleBodyFat.setTextColor(Color.parseColor("#8a979d"));
            imageViewFemale.setImageResource(R.drawable.female_avatar_unselect);
        }
        else if(view.getId() == R.id.layout_female_BodyFat){

            gender= "female";

            tvFemaleBodyFat.setTextColor(Color.WHITE);
            imageViewFemale.setImageResource(R.drawable.female_avatar_select);

            //changing male text and image
            tvMaleBodyFat.setTextColor(Color.parseColor("#8a979d"));
            imageViewMale.setImageResource(R.drawable.man_avatar_unselect);

        }


        //for footer
        switch (view.getId()){

            case R.id.layout_disease:
                intent = new Intent(this,Home_Remedies_Pro.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;

            case R.id.layout_plant:

                intent = new Intent(this,Health_Benefits_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

                break;
        }

    }
}
