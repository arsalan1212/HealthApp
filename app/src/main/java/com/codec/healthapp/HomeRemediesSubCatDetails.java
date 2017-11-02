package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Utils.MyAppUrl;
import adapter.HomeRemSubCatDetailAdapter;
import model.HomeRemSubCatModel;

public class HomeRemediesSubCatDetails extends AppCompatActivity {

    String subCategoryID;
    String categoryName;
    ArrayList arraylist_homeRemSubCat = new ArrayList();
    private RecyclerView mRecyclerView;
    private HomeRemSubCatDetailAdapter adapter;
    private ProgressBar mProgressbar;
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_remedies_sub_cat_details);

        tvTitle = findViewById(R.id.tvhomeRemediesDetail);

        mProgressbar = findViewById(R.id.progress_homeRemSubDetail);

        mRecyclerView = findViewById(R.id.homeRemidiesCatDetailRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        Intent intent = getIntent();

        if(intent!=null){

            subCategoryID = intent.getStringExtra("sub_cat_id");
            categoryName = intent.getStringExtra("category_name");

            tvTitle.setText("100+ Ailments "+categoryName);

        }

        FetchSubCategoryDetail();
    }
//back button
    public void GoBackbtn(View view){
        Intent intent = new Intent(this, Home_Remedies_Pro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    //fetching data from server
    private void FetchSubCategoryDetail() {

        StringRequest childRequest = new StringRequest(Request.Method.POST, MyAppUrl.HOME_REM_SUB_CATEGORY_URL+subCategoryID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        mRecyclerView.setVisibility(View.VISIBLE);
                        mProgressbar.setVisibility(View.GONE);

                        try {

                            JSONObject childJsonObject = new JSONObject(response);
                            if(!childJsonObject.getString("success").equals("0"))
                            {

                                JSONArray childJsonArray = childJsonObject.getJSONArray("category");
                                for(int i=0; i< childJsonArray.length(); i++) {

                                    JSONObject child_JsonObject = (JSONObject) childJsonArray.get(i);
                                    String detailID = child_JsonObject.getString("DETAIL_ID");
                                    String subID = child_JsonObject.getString("SUB_ID");
                                    String title = child_JsonObject.getString("TITLE_HEADING");
                                    String description = child_JsonObject.getString("DESCRIPTION");
                                    String image = child_JsonObject.getString("IMAGE");

                                    HomeRemSubCatModel helperModel = new HomeRemSubCatModel(
                                            detailID,
                                            subID,
                                            title,
                                            description,
                                            image
                                    );

                                    arraylist_homeRemSubCat.add(helperModel);
                                }

                                adapter = new HomeRemSubCatDetailAdapter(HomeRemediesSubCatDetails.this,arraylist_homeRemSubCat);
                                mRecyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mRecyclerView.setVisibility(View.GONE);
                mProgressbar.setVisibility(View.VISIBLE);
            }
        });

        MySingleton.getInstance(HomeRemediesSubCatDetails.this).addToRequestQueue(childRequest);

    }
}
