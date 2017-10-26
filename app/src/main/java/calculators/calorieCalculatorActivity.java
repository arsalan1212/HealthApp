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

public class calorieCalculatorActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText editTextAge,editTextWeight,editTextHeight;
    private RadioButton radioButtonMale,radioButtonFemale;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        mToolbar = findViewById(R.id.calorie_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Calorie Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initializating the Views
        editTextAge = findViewById(R.id.et_age);
        editTextHeight = findViewById(R.id.et_height);
        editTextWeight = findViewById(R.id.et_weight);

        textViewResult = findViewById(R.id.CalorieResult);

        radioButtonMale = findViewById(R.id.radioMale);
        radioButtonFemale = findViewById(R.id.radioFemale);
    }

    @Override
    public void onBackPressed() {
    }

    //calculate your calorie
    public void calculateCalorie(View view){

        String strAge = editTextAge.getText().toString().trim();
        String strHeight = editTextHeight.getText().toString().trim();
        String strWeight = editTextWeight.getText().toString().trim();

        if(!TextUtils.isEmpty(strAge) && !TextUtils.isEmpty(strHeight) && !TextUtils.isEmpty(strWeight)){

            int ageValue = Integer.parseInt(strAge);
            float heightValue = Float.parseFloat(strHeight);
            float weightValue = Float.parseFloat(strWeight);

            if(radioButtonMale.isChecked()){
                calculateMaleCalorie(ageValue,heightValue,weightValue);
            }
            else if(radioButtonFemale.isChecked()){
                calculateFemaleCalorie(ageValue,heightValue,weightValue);
            }
        }
        else{

            Toast.makeText(this, "Fill all the Required Fields", Toast.LENGTH_SHORT).show();
        }

    }

    //calculating Female calorie
    private void calculateFemaleCalorie(int age, float height, float weight) {

        DecimalFormat decimalformate = new DecimalFormat("######.####");

        //calculating calorie
        double femaleCalorie = 665.09 + (9.56 * weight) + (1.84 * height - (4.67 * age));

        double finalResult = Double.valueOf(decimalformate.format(femaleCalorie));

        textViewResult.setText("Your estimated daily metabolic rate is: "+finalResult+"\n\n You roughly need " +
                finalResult+" calories a day to maintain your current weight");
    }

    //calculating male calorie
    private void calculateMaleCalorie(int age, float height, float weight) {

        DecimalFormat decimalformate = new DecimalFormat("######.####");

        //calculating calorie
        double maleCalorie = 66.47 + (13.75 * weight) + (5.0 * height - (6.75 * age));
        double finalResult = Double.valueOf(decimalformate.format(maleCalorie));

        textViewResult.setText("Your estimated daily metabolic rate is: "+finalResult+"\n\n You roughly need " +
                finalResult+" calories a day to maintain your current weight");
    }
}
