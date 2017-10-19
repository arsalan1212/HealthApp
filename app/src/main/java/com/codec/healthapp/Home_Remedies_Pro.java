package com.codec.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Utils.MyAppUrl;
import model.HomeRemSubCatModel;
import model.HomeRemediesModel;

/**
 * Created by RNSolution on 11/10/2017.
 */

public class Home_Remedies_Pro extends AppCompatActivity {
    RecyclerView recycler;
    ArrayList<HomeRemediesModel> parent = new ArrayList<>();
    ArrayList<HomeRemSubCatModel> arraylistHomeRemSubCat= new ArrayList<>();
    ArrayList<ArrayList<HomeRemSubCatModel>> child= new ArrayList<>();
    LHV_Visit_History_Adapter adapter;
    private RelativeLayout layoutRecyclerView;
    private LinearLayout layoutConnection;
    private ProgressBar mProgressBar;
    private boolean isTimeFinish=false;
    @Override
    protected void onCreate(Bundle CreateBundle)
    {
        super.onCreate(CreateBundle);
        setContentView(R.layout.home_remedies_pro);

        initViews();
        FetchDataFromServer();
        new CountDownTimer(5000,1000){
            @Override
            public void onFinish() {
                isTimeFinish =true;
                fillAdapter();
            }

            @Override
            public void onTick(long l) {

            }
        }.start();

    }

    //initializing the views
    private void initViews(){
        recycler = (RecyclerView) findViewById(R.id.main_recycler);
        layoutConnection = findViewById(R.id.layout_Connection);
        layoutRecyclerView = findViewById(R.id.layout_RecyclerView);
        mProgressBar = findViewById(R.id.progresbar);

    }
    //fetching data from server
    private void FetchDataFromServer() {


        StringRequest request = new StringRequest(Request.Method.POST, MyAppUrl.HOME_REMEDIES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressBar.setVisibility(View.GONE);
                        layoutRecyclerView.setVisibility(View.VISIBLE);
                        layoutConnection.setVisibility(View.GONE);

                        if(response!=null){
                            try {
                                JSONObject parentJsonObject = new JSONObject(response);

                                final JSONArray parentJsonArray = parentJsonObject.getJSONArray("category");

                                for(int i=0; i< parentJsonArray.length(); i++){

                                    JSONObject childJsonObject = (JSONObject) parentJsonArray.get(i);

                                    String categoryId = childJsonObject.getString("CATEGORYID");
                                    String subCategoryId = childJsonObject.getString("SUB_CATEGORY_ID");
                                    String categoryName = childJsonObject.getString("SUB_CATE_NAME");

                                    HomeRemediesModel helper = new HomeRemediesModel(categoryId,subCategoryId,categoryName);

                                    parent.add(helper);

                                    //parent header response end

                                    //child request start
                                    fetchChildResponse(parent.get(i).getSubCategoryID());
                                    //child request end
                                    Log.e("size of Child","SIze of child: "+child.size() );
                                }

/*                                adapter = new LHV_Visit_History_Adapter(Home_Remedies_Pro.this, parent, child,Home_Remedies_Pro.this);
                                adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
                                recycler.setLayoutManager(new LinearLayoutManager(Home_Remedies_Pro.this));
                                recycler.setAdapter(adapter);*/
                                fillAdapter();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("TAG","Error: "+error.getMessage());
                mProgressBar.setVisibility(View.GONE);

                if(error instanceof NetworkError){
                    layoutConnection.setVisibility(View.VISIBLE);
                    layoutRecyclerView.setVisibility(View.GONE);
                }
                else if(error instanceof NoConnectionError){
                    layoutConnection.setVisibility(View.VISIBLE);
                    layoutRecyclerView.setVisibility(View.GONE);
                }
            }
        });

        MySingleton.getInstance(Home_Remedies_Pro.this).addToRequestQueue(request);
    }
//fetching subcategory of each parent Header
    private void fetchChildResponse(final String subCatID){

        StringRequest childRequest = new StringRequest(Request.Method.POST, MyAppUrl.HOME_REM_SUB_CATEGORY_URL+subCatID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject childJsonObject = null;
                        try {

                            childJsonObject = new JSONObject(response);
                            if(!childJsonObject.getString("success").equals("0"))
                            {

                                JSONArray childJsonArray = childJsonObject.getJSONArray("category");

                                for(int i=0; i< childJsonArray.length(); i++) {

                                  Log.e("TAG","RESPONSE for Iteration :"+i+" and SUB Cat Ud id is: "+subCatID+"   "+response);

                                    JSONObject child_JsonObject = (JSONObject) childJsonArray.get(i);

                                    String detailID = child_JsonObject.getString("DETAIL_ID");
                                    String subID = child_JsonObject.getString("SUB_ID");
                                    String title = child_JsonObject.getString("TITLE_HEADING");
                                    String description = child_JsonObject.getString("DESCRIPTION");
                                    String image = child_JsonObject.getString("IMAGE");

                                    Log.e("KHAN","tITLE: "+title+"  Description: "+description+"\n");
                                    HomeRemSubCatModel helperModel = new HomeRemSubCatModel(
                                            detailID,
                                            subID,
                                            title,
                                            description,
                                            image
                                    );

                                    arraylistHomeRemSubCat.add(helperModel);
                                }
                                child.add(arraylistHomeRemSubCat);
                                fillAdapter();
                                Log.e("omer",""+child.size());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("khan",e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("TAG","SUB CAT RESPONSE ERROR: "+error.getMessage());
            }
        });
        MySingleton.getInstance(Home_Remedies_Pro.this).addToRequestQueue(childRequest);
        //child request end
    }
    private void fillAdapter(){


        if(!child.isEmpty() && !parent.isEmpty() && isTimeFinish){

            adapter = new LHV_Visit_History_Adapter(Home_Remedies_Pro.this, parent, child,Home_Remedies_Pro.this);
            adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
            recycler.setLayoutManager(new LinearLayoutManager(Home_Remedies_Pro.this));
            recycler.setAdapter(adapter);
            printArray();
        }

    }
    private void printArray(){
        ArrayList<HomeRemSubCatModel> arrayList= new ArrayList<>();

        for(int i=0;i< child.size();i++){
            arrayList = child.get(i);
            Log.e("Parent of Child","Parent Size: "+arrayList.size());
        }

        for(int j=0; j< arrayList.size(); j++){

            Log.e("Home","Items of Home: "+ arrayList.get(j).getTitleHeading());
        }
    }
    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }

    //Refresh Button
    public void RefreshButton(View view){
        Intent intent = new Intent(this,Home_Remedies_Pro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
