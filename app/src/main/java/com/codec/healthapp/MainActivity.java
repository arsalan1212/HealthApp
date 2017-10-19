package com.codec.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView home_button,best_food_button,rich_food_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_button  = (ImageView) findViewById(R.id.home_button);
        best_food_button  = (ImageView) findViewById(R.id.best_food_button);
        rich_food_button  = (ImageView) findViewById(R.id.rich_food_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(MainActivity.this,Home_Remedies_Pro.class);
                startActivity(obj);
            }
        });
        best_food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(MainActivity.this,Best_Food_Body_Wise.class);
                startActivity(obj);
            }
        });
        rich_food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(MainActivity.this,Rich_Food.class);
                startActivity(obj);
            }
        });
    }
}
