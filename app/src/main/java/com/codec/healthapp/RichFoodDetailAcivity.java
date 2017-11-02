package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.RichFoodDetailAdapter;
import model.RichFoodDetailModel;

public class RichFoodDetailAcivity extends AppCompatActivity {

    private TextView tvRichFoodTitle;
    private RecyclerView mRecyclerView;
    private ArrayList<RichFoodDetailModel> arrayListRichFoodDetail;
    private RichFoodDetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_food_detail_acivity);

        tvRichFoodTitle = findViewById(R.id.tvRichFoodTitle);


        mRecyclerView = findViewById(R.id.recyclerView_richFood);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);


        fillDummyData();

    }

    //for testing
    private void fillDummyData() {
        RichFoodDetailModel model = new RichFoodDetailModel(
                "escription1 of Aaaa description1 of Aaaa description1 " +
                        "of Aaaa description1 of Aaaa description1 of Aaaa",
                "http://projects.multibizservices.com/HEALTHY_HOME/asset/upload/health_benefits.jpg"
        );

        RichFoodDetailModel model1 = new RichFoodDetailModel(
                "escription2 of Aaaa",
                "http://projects.multibizservices.com/HEALTHY_HOME/asset/upload/health_benefits.jpg"
        );

        arrayListRichFoodDetail = new ArrayList<>();

        arrayListRichFoodDetail.add(model);
        arrayListRichFoodDetail.add(model1);

        adapter = new RichFoodDetailAdapter(this, arrayListRichFoodDetail);
        mRecyclerView.setAdapter(adapter);

    }

    //Back button click listiner
    public void GoBackbtn(View view){
        Intent intent = new Intent(this, Rich_Food.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Rich_Food.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}
