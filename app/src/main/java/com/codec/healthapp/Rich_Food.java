package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import adapter.ViewPagerAdapter;

/**
 * Created by RNSolution on 11/10/2017.
 */

public class Rich_Food extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle createBundle)
    {
        super.onCreate(createBundle);
        setContentView(R.layout.rich_food);

        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
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
}
