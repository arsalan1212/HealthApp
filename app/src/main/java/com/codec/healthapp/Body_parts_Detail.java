package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Body_parts_Detail extends AppCompatActivity {

    String titleArray[];
    String descriptionArray[];
    int imageArray[];
    String bodyPartName;
    private Toolbar mToolbar;

    CircleImageView circleImageView1,circleImageView2,circleImageView3;
    TextView tvTitle1,tvTitle2,tvTitle3,tvBodypartTitle;
    TextView tvDescription1,tvDescription2,tvDescription3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_parts__detail);

        circleImageView1 = findViewById(R.id.bodyPart_image1);
        circleImageView2 = findViewById(R.id.bodyPart_image2);
        circleImageView3 = findViewById(R.id.bodyPart_image3);

        tvTitle1 = findViewById(R.id.tvBodyPartTitle1);
        tvTitle2 = findViewById(R.id.tvBodyPartTitle2);
        tvTitle3 = findViewById(R.id.tvBodyPartTitle3);

        tvDescription1 = findViewById(R.id.tvBodyPartDescription1);
        tvDescription2 = findViewById(R.id.tvBodyPartDescription2);
        tvDescription3 = findViewById(R.id.tvBodyPartDescription3);

        tvBodypartTitle = findViewById(R.id.tvbodyPartDetailTitle);

        Intent intent = getIntent();

        if(intent!=null){

            titleArray = intent.getStringArrayExtra("titleArray");
            descriptionArray = intent.getStringArrayExtra("descriptionArray");
            imageArray = intent.getIntArrayExtra("imageArray");
            bodyPartName = intent.getStringExtra("bodypartName");
        }


        tvBodypartTitle.setText(bodyPartName);

        //by filling data into the Views
        setViews();
    }
//setting data in the Views
    private void setViews() {
        circleImageView1.setImageResource(imageArray[0]);
        circleImageView2.setImageResource(imageArray[1]);
        circleImageView3.setImageResource(imageArray[2]);


        tvTitle1.setText(titleArray[0]);
        tvTitle2.setText(titleArray[1]);
        tvTitle3.setText(titleArray[2]);

        tvDescription1.setText(descriptionArray[0]);
        tvDescription2.setText(descriptionArray[1]);
        tvDescription3.setText(descriptionArray[2]);
    }


    //back button
    public void GoBackbtn(View view){
        Intent intent = new Intent(this, Best_Food_Body_Wise.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
