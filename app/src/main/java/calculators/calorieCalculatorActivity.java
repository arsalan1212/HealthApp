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

import com.codec.healthapp.MainActivity;
import com.codec.healthapp.R;

import java.text.DecimalFormat;

public class calorieCalculatorActivity extends AppCompatActivity  implements View.OnClickListener{


    private EditText editTextAge,editTextWeight,editTextHeight;
    private LinearLayout layout_male, layout_female;
    private TextView tvMaleCalorie, tvFemaleCalorie,tvCalorieTitle;
    private ImageView imageViewFemale, imageViewMale;
    private String gender="";
    private String strSpinnerWeight, strSpinnerHeight;
    private Spinner mSpinnerHeightCalorie,mSpinnerWeightCalorie;
    private String[] weightParams,heightParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        //setting custom font
        tvCalorieTitle = findViewById(R.id.tvCalorieTitle);
        Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/sansation_light.ttf");
        tvCalorieTitle.setTypeface(typeface);

        weightParams = getResources().getStringArray(R.array.bmi_weight);
        heightParams = getResources().getStringArray(R.array.calorie_height);
        // initializating the Views
        editTextAge = findViewById(R.id.et_age);
        editTextHeight = findViewById(R.id.et_height);
        editTextWeight = findViewById(R.id.et_weight);

        layout_male = findViewById(R.id.layout_male_calorie);
        layout_female = findViewById(R.id.layout_female_calorie);

        tvMaleCalorie = findViewById(R.id.tvMaleCalorie);
        tvFemaleCalorie = findViewById(R.id.tvFemaleCalorie);

        imageViewMale = findViewById(R.id.imageMaleAvatorCalorie);
        imageViewFemale = findViewById(R.id.imageFemaleAvatorCalorie);

        layout_male.setOnClickListener(this);
        layout_female.setOnClickListener(this);

        mSpinnerWeightCalorie = findViewById(R.id.spinner_weight_calorie);
        mSpinnerHeightCalorie = findViewById(R.id.spinner_height_calorie);

        mSpinnerWeightCalorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strSpinnerWeight = weightParams[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerHeightCalorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strSpinnerHeight = heightParams[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    //calculate your calorie
    public void calculateCalorie(View view){

        String strAge = editTextAge.getText().toString().trim();
        String strHeight = editTextHeight.getText().toString().trim();
        String strWeight = editTextWeight.getText().toString().trim();

        if(!gender.equals("")){

            if(!TextUtils.isEmpty(strAge) && !TextUtils.isEmpty(strHeight) && !TextUtils.isEmpty(strWeight)){

                int ageValue = Integer.parseInt(strAge);
                float heightValue = Float.parseFloat(strHeight);
                float weightValue = Float.parseFloat(strWeight);

                double weight=0,height=0;

                if(strSpinnerWeight.equals("lbs"))
                {
                    weight= weightValue * 0.45359237;

                }else if(strSpinnerWeight.equals("Kgs")){
                    weight = weightValue;
                }

                if(strSpinnerHeight.equals("Inches")){
                    height = heightValue * 2.54;
                }
                else if(strSpinnerHeight.equals("Cms")){
                    height = heightValue;
                }

                if(gender.equals("male")){
                    calculateMaleCalorie(ageValue,height,weight);
                }
                else if(gender.equals("female")){
                    calculateFemaleCalorie(ageValue,height,weight);
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

    //calculating Female calorie
    private void calculateFemaleCalorie(int age, double height, double weight) {

        DecimalFormat decimalformate = new DecimalFormat("######.####");

        //calculating calorie
        double femaleCalorie = 665.09 + (9.56 * weight) + (1.84 * height - (4.67 * age));

        double finalResult = Double.valueOf(decimalformate.format(femaleCalorie));

        showDialogResult(finalResult);
    }

    //calculating male calorie
    private void calculateMaleCalorie(int age, double height, double weight) {

        DecimalFormat decimalformate = new DecimalFormat("######.####");

        //calculating calorie
        double maleCalorie = 66.47 + (13.75 * weight) + (5.0 * height - (6.75 * age));
        double finalResult = Double.valueOf(decimalformate.format(maleCalorie));


        showDialogResult(finalResult);
    }

    //showing the dialog
    private void showDialogResult(double finalResult) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.result_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(false);

        TextView tvWHRResult = dialog.findViewById(R.id.textViewResult);
        TextView tvResultHeading = dialog.findViewById(R.id.textViewResultHeading);
        TextView tvResultRange = dialog.findViewById(R.id.textViewResultRange);
        ImageView imageViewClose = dialog.findViewById(R.id.imageView_dialog_close);


        tvWHRResult.setText("Your estimated daily metabolic rate is:\n"+finalResult);
        tvResultHeading.setText("It suggest that You roughly need:");
        tvResultRange.setText(finalResult+" calories a day to maintain your current weight");
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //Back button click listiner
    public void GoBackbtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.layout_male_calorie){

            gender ="male";

            tvMaleCalorie.setTextColor(Color.WHITE);
            imageViewMale.setImageResource(R.drawable.man_avatar_select);

            //changing female text and image
            tvFemaleCalorie.setTextColor(Color.parseColor("#8a979d"));
            imageViewFemale.setImageResource(R.drawable.female_avatar_unselect);
        }
        else if(view.getId() == R.id.layout_female_calorie){

            gender="female";

            tvFemaleCalorie.setTextColor(Color.WHITE);
            imageViewFemale.setImageResource(R.drawable.female_avatar_select);

            //changing male text and image
            tvMaleCalorie.setTextColor(Color.parseColor("#8a979d"));
            imageViewMale.setImageResource(R.drawable.man_avatar_unselect);


        }
    }
}
