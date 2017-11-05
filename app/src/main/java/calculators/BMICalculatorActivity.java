package calculators;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.text.DecimalFormat;

public class BMICalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText height;
    private EditText weight;
    private TextView textViewMale,textViewFemale,tvBMITitle;
    private LinearLayout layout_male,layout_female,layout_disease,layout_plant;
    private ImageView imageViewMale,imageViewFemale;
    private String gender="";
    private Spinner mSpinnerHeightBMI,mSpinnerWeightBMI;
    private String strSpinnerHeight,strSpinnerWeight;
    private String[] array_weight,array_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        array_height = getResources().getStringArray(R.array.bmi_height);
        array_weight = getResources().getStringArray(R.array.bmi_weight);

        //setting title custom font
        tvBMITitle = findViewById(R.id.tv_BMItitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/sansation_light.ttf");
        tvBMITitle.setTypeface(typeface);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);

        layout_male = findViewById(R.id.layout_male_BMI);
        layout_female = findViewById(R.id.layout_female_BMI);

        imageViewMale = findViewById(R.id.imageMaleAvatorBMI);
        imageViewFemale = findViewById(R.id.imageFemaleAvatorBMI);

        textViewMale = findViewById(R.id.tvMaleBMI);
        textViewFemale = findViewById(R.id.tvFemaleBMI);

        layout_disease = findViewById(R.id.layout_disease);
        layout_plant = findViewById(R.id.layout_plant);

        layout_disease.setOnClickListener(this);
        layout_plant.setOnClickListener(this);

        layout_male.setOnClickListener(this);
        layout_female.setOnClickListener(this);


        mSpinnerHeightBMI = findViewById(R.id.spinner_height_bmi);
        mSpinnerWeightBMI = findViewById(R.id.spinner_weight_bmi);

        mSpinnerWeightBMI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strSpinnerWeight = array_weight[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerHeightBMI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strSpinnerHeight = array_height[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void calculateBMI(View view){

        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if(!gender.equals("")){
            if (heightStr != null && !"".equals(heightStr)
                    && weightStr != null  &&  !"".equals(weightStr)) {

                try{
                    float heightValue = Float.parseFloat(heightStr);
                    float weightValue = Float.parseFloat(weightStr);

                    double weight =0;
                    double height =0;

                    if(strSpinnerWeight.equals("lbs")){
                        weight = weightValue/2.2046;
                    }
                    else if(strSpinnerWeight.equals("Kgs")){
                        weight = weightValue;
                    }
                    if(strSpinnerHeight.equals("Inches")){
                        height = heightValue * 0.0254;
                    }
                    else if(strSpinnerHeight.equals("Meter")){
                        height =heightValue;
                    }
                    if(weight!= 0 && height!=0 ){

                        double bmi = weight / (height * height);

                        DecimalFormat decimalFormat = new DecimalFormat("##.#");
                        double finalBMI=Double.parseDouble(decimalFormat.format(bmi));
                        displayBMI(finalBMI);
                    }



                }
                catch (Exception e){

                    Toast.makeText(this, "Enter Valid Value", Toast.LENGTH_SHORT).show();
                    Log.d("TAG","Error: "+e.getMessage());
                }
            }
            else{
                Toast.makeText(this, "Fill All the Require Fields", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "First Select Your Gender", Toast.LENGTH_SHORT).show();
        }

    }

    private void displayBMI(double bmi) {
        String bmiLabel = "";

        if (Double.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Double.compare(bmi, 15f) > 0  &&  Double.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Double.compare(bmi, 16f) > 0  &&  Double.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Double.compare(bmi, 18.5f) > 0  &&  Double.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Double.compare(bmi, 25f) > 0  &&  Double.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Double.compare(bmi, 30f) > 0  &&  Double.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Double.compare(bmi, 35f) > 0  &&  Double.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        //showing the dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.result_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView tvResult = dialog.findViewById(R.id.textViewResult);
        TextView tvResultHeading = dialog.findViewById(R.id.textViewResultHeading);
        TextView tvResultRange = dialog.findViewById(R.id.textViewResultRange);
        ImageView imageViewClose = dialog.findViewById(R.id.imageView_dialog_close);

        tvResult.setText("Your BMI is:\n"+bmi);
        tvResultHeading.setText("It suggest that your weight is in:");
        tvResultRange.setText(bmiLabel+ " Range");

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
        if(view.getId() == R.id.layout_male_BMI){

            gender= "male";

            imageViewMale.setImageResource(R.drawable.man_avatar_select);
            textViewMale.setTextColor(Color.WHITE);

            //setting view for female
            imageViewFemale.setImageResource(R.drawable.female_avatar_unselect);
            textViewFemale.setTextColor(Color.parseColor("#8a979d"));
        }
        else if(view.getId() == R.id.layout_female_BMI){

            gender ="female";


            imageViewFemale.setImageResource(R.drawable.female_avatar_select);
            textViewFemale.setTextColor(Color.WHITE);

            //setting view for female
            imageViewMale.setImageResource(R.drawable.man_avatar_unselect);
            textViewMale.setTextColor(Color.parseColor("#8a979d"));
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
