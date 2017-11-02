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

public class WHRCalculatorActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextWaist,editTextHip;
    private TextView tvFemale,tvMale,tvWHRTitle;
    private LinearLayout layoutMale,layoutFemale;
    private ImageView imageMaleAvator,imageFemaleAvator;
    private String[] waise_array ;
    private Spinner mSpinner_waist,mSpinnerHip;
    String gender="";
    String waistParams;
    String hipParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whrcalculator);

        //setting custom font
        tvWHRTitle = findViewById(R.id.tv_waist_hip_ratio);
        Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/sansation_light.ttf");
        tvWHRTitle.setTypeface(typeface);

        waise_array =getResources().getStringArray(R.array.waise_array);
        editTextWaist = findViewById(R.id.et_waist);
        editTextHip = findViewById(R.id.et_hip);

        layoutMale= findViewById(R.id.layout_male);
        layoutFemale = findViewById(R.id.layout_female);

        tvMale = findViewById(R.id.tvMale);
        tvFemale = findViewById(R.id.tvFemale);
        imageMaleAvator = findViewById(R.id.imageMaleAvator);
        imageFemaleAvator = findViewById(R.id.imageFemaleAvator);


        layoutMale.setOnClickListener(this);
        layoutFemale.setOnClickListener(this);

        mSpinner_waist = findViewById(R.id.spinner_waist);
        mSpinnerHip = findViewById(R.id.spinner_hip);

        mSpinner_waist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                waistParams = waise_array[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerHip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hipParams = waise_array[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    //calculating WHR
    public void calculateWHR(View view){

        String strWaist = editTextWaist.getText().toString().trim();
        String strHip = editTextHip.getText().toString().trim();

        if(!gender.equals("")){
            if(!TextUtils.isEmpty(strWaist) && !TextUtils.isEmpty(strHip)){

                try{

                    float waistValue = Float.parseFloat(strWaist);
                    float hipValue = Float.parseFloat(strHip);

                    double hip=0;
                    double waist=0;
                    if(waistParams.equals("Cms")){

                        waist = (waistValue * 0.39370);
                    }
                    else if(waistParams.equals("Inches")){
                        waist =waistValue;
                    }

                    if(hipParams.equals("Cms"))
                    {
                        hip =  (hipValue * 0.39370);
                    }
                    else if(hipParams.equals("Inches")){
                        hip = hipValue;
                    }

                    DecimalFormat myformate = new DecimalFormat("#.##");

                    double WHR = waist/hip;

                    double roundOffWHR = Double.parseDouble(myformate.format(WHR));

                    if(gender!="" && gender.equals("male")){

                        WHRHealthRangeForMale(roundOffWHR);
                    }
                    else if(gender!="" && gender.equals("female")){

                        WHRHealthRangeForFeMale(roundOffWHR);
                    }
                }
                catch (Exception e){
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
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

    //WHR Health range for male
    private void WHRHealthRangeForMale(double WHR) {

        String result="";

        if(WHR <=0.95){
            result ="Low";
        }
        else if(WHR > 0.95 && WHR < 1.0){
            result ="Moderate";
        }
        else if(WHR >= 1.0){
            result ="High";
        }
        ShowDialog(WHR, result);

    }
    //WHR Health range for Female
    private void WHRHealthRangeForFeMale(double WHR) {

        String result="";

        if(WHR <= 0.80){
            result ="Low";
        }
        else if(WHR > 0.80 && WHR <= 0.85){
            result ="Moderate";
        }
        else if(WHR >= 0.86){
            result ="High";
        }

        ShowDialog(WHR, result);


    }

    private void ShowDialog(double WHR, String result) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.result_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setCancelable(false);

        TextView tvWHRResult = dialog.findViewById(R.id.textViewResult);
        TextView tvResultHeading = dialog.findViewById(R.id.textViewResultHeading);
        TextView tvResultRange = dialog.findViewById(R.id.textViewResultRange);
        ImageView imageViewClose = dialog.findViewById(R.id.imageView_dialog_close);


        tvWHRResult.setText("Your WHR is:\n"+WHR);
        tvResultHeading.setText("It suggest that Your Health condition is at:");
        tvResultRange.setText(result+" Risk Range");
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void GoBackbtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.layout_male){

            gender = "male";
            tvMale.setTextColor(Color.WHITE);
            imageMaleAvator.setImageResource(R.drawable.man_avatar_select);

            //changing female text and image
            tvFemale.setTextColor(Color.parseColor("#8a979d"));
            imageFemaleAvator.setImageResource(R.drawable.female_avatar_unselect);
        }
        else if(view.getId() == R.id.layout_female){
            gender = "female";
            tvFemale.setTextColor(Color.WHITE);
            imageFemaleAvator.setImageResource(R.drawable.female_avatar_select);

            //changing male text and image
            tvMale.setTextColor(Color.parseColor("#8a979d"));
            imageMaleAvator.setImageResource(R.drawable.man_avatar_unselect);
        }

    }
}
