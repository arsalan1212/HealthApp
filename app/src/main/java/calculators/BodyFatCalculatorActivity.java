package calculators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codec.healthapp.R;

public class BodyFatCalculatorActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText editTextWeight,editTextWaist;
    private RadioButton radioButtonMale,radioButtonFemale;
    private TextView tvBodyFatResult,tvBodyFatDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_fat_calculator);

        mToolbar = findViewById(R.id.bodyfat_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Body Fat Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextWeight = findViewById(R.id.et_BodyFatWeight);
        editTextWaist = findViewById(R.id.et_BodyFatWaist);

        tvBodyFatResult = findViewById(R.id.BodyFatResult);
        tvBodyFatDescription = findViewById(R.id.tvBodyFatDescription);

        radioButtonMale = findViewById(R.id.radioBodyFatMale);
        radioButtonFemale = findViewById(R.id.radioBodyFatFemale);

    }

    @Override
    public void onBackPressed() {
    }

    //calculating the body fat
    public void calculateBodyFat(View view){


        String strWeight = editTextWeight.getText().toString().trim();
        String strWaist = editTextWaist.getText().toString().trim();


        if( !TextUtils.isEmpty(strWeight) && !TextUtils.isEmpty(strWaist)){

            float weightValue = Float.parseFloat(strWeight);
            float waistValue = Float.parseFloat(strWaist);

            if( weightValue >0  && waistValue >0){

                if(radioButtonMale.isChecked()){

                    calculateMaleBodyFat(weightValue,waistValue);
                }
                else if(radioButtonFemale.isChecked()){

                    calculateFemaleBodyFat(weightValue,waistValue);
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

    //calculating the Male body Fat
    private void calculateMaleBodyFat( float weightValue, float waistValue)
    {
        String result="";
        double BF = -98.42 + 4.15 * waistValue - .082 * weightValue;
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

        tvBodyFatResult.setText("Your Body Fat is: "+BFPercent +" %");

        if(BFPercent >=2){
            tvBodyFatDescription.setVisibility(View.VISIBLE);
            tvBodyFatDescription.setText("Body Fat type is: "+result);

        }


    }

    //calculating the Female body Fat
    private void calculateFemaleBodyFat( float weightValue, float waistValue) {

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

        tvBodyFatResult.setText("Your Body Fat is: "+BFPercent +" %");

        if(BFPercent >=10){
            tvBodyFatDescription.setVisibility(View.VISIBLE);
            tvBodyFatDescription.setText("Body Fat type is: "+result);
        }


    }

}
