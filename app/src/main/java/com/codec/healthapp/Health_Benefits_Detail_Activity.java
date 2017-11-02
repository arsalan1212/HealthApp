package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import adapter.HealthBenefitDetailAdapter;
import model.HealthBenefitDetailModel;

public class Health_Benefits_Detail_Activity extends AppCompatActivity {

    private ArrayList<HealthBenefitDetailModel> arrayList_healthBenefitDetail=new ArrayList<>();
    String subCategoryID;
    String categoryName;
    private TextView tv_toolbarTitle;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private HealthBenefitDetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__benefits__detail_);

        tv_toolbarTitle = findViewById(R.id.toolbar_title);
        mProgressbar = findViewById(R.id.progress_healtBenefitDetail);
        mRecyclerView = findViewById(R.id.HealthBenefitDetailRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        subCategoryID = intent.getStringExtra("sub_category_id");
        categoryName = intent.getStringExtra("category_name");

        tv_toolbarTitle.setText(categoryName);


        //fetch data of health benefit detail
        FetchDataFromServer();

    }

    //fetching the data from server
    private void FetchDataFromServer() {
        StringRequest childRequest = new StringRequest(Request.Method.POST, MyAppUrl.HEALTH_BENEFITS_CATEGORY_DETAIL_URL+subCategoryID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        mRecyclerView.setVisibility(View.VISIBLE);
                        mProgressbar.setVisibility(View.GONE);

                        try {

                            Log.d("TAG","Response: "+response);
                            JSONObject childJsonObject = new JSONObject(response);
                            if(!childJsonObject.getString("success").equals("0"))
                            {

                                JSONArray childJsonArray = childJsonObject.getJSONArray("detail");
                                for(int i=0; i< childJsonArray.length(); i++) {

                                    JSONObject child_JsonObject = (JSONObject) childJsonArray.get(i);
                                    String benefits = child_JsonObject.getString("BENEFITS");
                                    String richsource = child_JsonObject.getString("RICHSOURCE");

                                    HealthBenefitDetailModel helperModel = new HealthBenefitDetailModel(
                                           benefits,
                                            richsource
                                    );

                                    arrayList_healthBenefitDetail.add(helperModel);

                                }

                                    adapter = new HealthBenefitDetailAdapter(Health_Benefits_Detail_Activity.this,arrayList_healthBenefitDetail);
                                    mRecyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("TAG","Error: "+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mRecyclerView.setVisibility(View.GONE);
                mProgressbar.setVisibility(View.VISIBLE);
            }
        });

        MySingleton.getInstance(Health_Benefits_Detail_Activity.this).addToRequestQueue(childRequest);
    }

    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, Health_Benefits_Activity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }

}
