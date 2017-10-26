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

import java.text.DecimalFormat;

public class WHRCalculatorActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText editTextWaist,editTextHip;
    private TextView textViewResult,textViewDiseaseRisk;
    private RadioButton radioButtonMale,radioButtonFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whrcalculator);

        mToolbar = findViewById(R.id.whr_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("WHR Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextWaist = findViewById(R.id.et_waist);
        editTextHip = findViewById(R.id.et_hip);

        textViewResult = findViewById(R.id.WHRResult);
        textViewDiseaseRisk = findViewById(R.id.tvDiseaseRisk);

        radioButtonMale = findViewById(R.id.radioWHRMale);
        radioButtonFemale = findViewById(R.id.radioWHRFemale);

    }

    //calculating WHR
    public void calculateWHR(View view){

        String strWaist = editTextWaist.getText().toString().trim();
        String strHip = editTextHip.getText().toString().trim();

        if(!TextUtils.isEmpty(strWaist) && !TextUtils.isEmpty(strHip)){

            float waistValue = Float.parseFloat(strWaist);
            float hipValue = Float.parseFloat(strHip);

            DecimalFormat myformate = new DecimalFormat("#.##");
            double WHR = waistValue/hipValue;

            double roundOffWHR = Double.parseDouble(myformate.format(WHR));

            if(radioButtonMale.isChecked()){

                WHRHealthRangeForMale(roundOffWHR);
            }
            else if(radioButtonFemale.isChecked()){

                WHRHealthRangeForFeMale(roundOffWHR);
            }
        }
        else{

            Toast.makeText(this, "Fill all the Required Fields", Toast.LENGTH_SHORT).show();
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

        textViewResult.setText("Your WHR is: "+WHR);
        textViewDiseaseRisk.setVisibility(View.VISIBLE);
        textViewDiseaseRisk.setText("Your Health condition is at "+result+" Risk");

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

        textViewResult.setText("Your WHR is: "+WHR);
        textViewDiseaseRisk.setVisibility(View.VISIBLE);
        textViewDiseaseRisk.setText("Your Health condition is at "+result+" Risk");

    }


    @Override
    public void onBackPressed() {
    }
}
