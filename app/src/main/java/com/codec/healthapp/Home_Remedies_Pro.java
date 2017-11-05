package com.codec.healthapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Utils.MyAppUrl;
import adapter.LHV_Visit_History_Adapter;
import model.HomeRemediesModel;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by RNSolution on 11/10/2017.
 */

public class Home_Remedies_Pro extends AppCompatActivity {
    StickyListHeadersListView stickyList;
    ArrayList<HomeRemediesModel> parent = new ArrayList<>();
    LHV_Visit_History_Adapter adapter;
    private RelativeLayout layoutRecyclerView;
    private LinearLayout layoutConnection;
    private ProgressBar mProgressBar;
    private EditText editTextSearch;
    private TextView tvNoRecordFound;
    @Override
    protected void onCreate(Bundle CreateBundle)
    {
        super.onCreate(CreateBundle);
        setContentView(R.layout.home_remedies_pro);

        initViews();
        FetchDataFromServer();
    }


    //initializing the views
    private void initViews(){
        stickyList = (StickyListHeadersListView) findViewById(R.id.homeRemediesList);

        layoutConnection = findViewById(R.id.layout_Connection);
        layoutRecyclerView = findViewById(R.id.layout_RecyclerView);
        mProgressBar = findViewById(R.id.progresbar);

        tvNoRecordFound = findViewById(R.id.tvNoRecorFound);

        editTextSearch = findViewById(R.id.searchcard);
        editTextSearch.addTextChangedListener(mTextwatcher);

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

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

    private TextWatcher mTextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            boolean isFound =false;
            ArrayList<HomeRemediesModel> filterList = new ArrayList<>();
            String newText = String.valueOf(charSequence).toLowerCase();

            if(!TextUtils.isEmpty(newText)){

                for(int j=0; j<parent.size();j++){

                    String categoryTitle = parent.get(j).getCategoryName().toLowerCase();

                    if(categoryTitle.contains(newText)){

                        isFound= true;
                        filterList.add(parent.get(j));
                    }else{
                        isFound = false;
                    }

                }


                if(filterList.size()!=0){

                    tvNoRecordFound.setVisibility(View.GONE);
                    stickyList.setVisibility(View.VISIBLE);
                }
                else if(!isFound && !newText.equals("")){
                    tvNoRecordFound.setVisibility(View.VISIBLE);
                    stickyList.setVisibility(View.VISIBLE);
                    adapter.setFilter(parent);
                }
                else{
                    tvNoRecordFound.setVisibility(View.VISIBLE);
                    stickyList.setVisibility(View.GONE);
                }
                adapter.setFilter(filterList);
                adapter.notifyDataSetChanged();
            }
            else{

                tvNoRecordFound.setVisibility(View.GONE);
                stickyList.setVisibility(View.VISIBLE);
                adapter.setFilter(parent);
                adapter.notifyDataSetChanged();
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };
    //fetching data from server
    private void FetchDataFromServer() {


        StringRequest request = new StringRequest(Request.Method.POST, MyAppUrl.HOME_REMEDIES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response!=null){
                            mProgressBar.setVisibility(View.GONE);
                            layoutRecyclerView.setVisibility(View.VISIBLE);
                            layoutConnection.setVisibility(View.GONE);

                            try {
                                JSONObject parentJsonObject = new JSONObject(response);

                                final JSONArray parentJsonArray = parentJsonObject.getJSONArray("category");

                                for(int i=0; i< parentJsonArray.length(); i++){

                                    JSONObject childJsonObject = (JSONObject) parentJsonArray.get(i);

                                    String categoryId = childJsonObject.getString("CATEGORYID");
                                    String subCategoryId = childJsonObject.getString("SUB_CATEGORY_ID");
                                    String categoryName = childJsonObject.getString("SUB_CATE_NAME");

                                    HomeRemediesModel helper = new HomeRemediesModel();

                                    helper.setCategoryID(categoryId);
                                    helper.setCategoryName(categoryName);
                                    helper.setSubCategoryID(subCategoryId);

                                    parent.add(helper);
                                }

                                adapter = new LHV_Visit_History_Adapter( parent,Home_Remedies_Pro.this);
                                stickyList.setAdapter(adapter);

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
