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
import adapter.HealthBenefitsAdapter;
import model.HealthBenefitsModel;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class Health_Benefits_Activity extends AppCompatActivity {

    private StickyListHeadersListView stickyListHeadersListView;
    private LinearLayout layoutConnection;
    private TextView tvNoRecordFound;
    private ProgressBar mProgress;
    private RelativeLayout layoutStickyList;
    private ArrayList<HealthBenefitsModel> arraylist_healthBenefits;
    private HealthBenefitsAdapter adapter;
    private EditText editTextSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__benefits_);


        //initializing the Views
        initViews();

        //Fetch data from Server
        FetchDataOfHealthBenefits();
    }

    private void initViews() {
        stickyListHeadersListView = findViewById(R.id.HealthBenefitList);
        layoutConnection = findViewById(R.id.layout_Connection);
        tvNoRecordFound = findViewById(R.id.tvNoRecordFound);
        mProgress = findViewById(R.id.progresbar);
        arraylist_healthBenefits = new ArrayList<>();
        layoutStickyList = findViewById(R.id.layout_StickList);
        editTextSearch = findViewById(R.id.searchcard);

        editTextSearch.addTextChangedListener(textWatcher);

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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            boolean isFound =false;
            ArrayList<HealthBenefitsModel> filterList = new ArrayList<>();
            String newText = String.valueOf(charSequence).toLowerCase();

            if(!TextUtils.isEmpty(newText)){

                for(int j=0; j<arraylist_healthBenefits.size();j++){

                    String categoryTitle = arraylist_healthBenefits.get(j).getCategoryName().toLowerCase();

                    if(categoryTitle.contains(newText)){

                        isFound= true;
                        filterList.add(arraylist_healthBenefits.get(j));
                    }else{
                        isFound = false;
                    }

                }


                if(filterList.size()!=0){

                    tvNoRecordFound.setVisibility(View.GONE);
                    stickyListHeadersListView.setVisibility(View.VISIBLE);
                }
                else if(!isFound && !newText.equals("")){
                    tvNoRecordFound.setVisibility(View.VISIBLE);
                    stickyListHeadersListView.setVisibility(View.VISIBLE);
                    adapter.setFilter(arraylist_healthBenefits);
                }
                else{
                    tvNoRecordFound.setVisibility(View.VISIBLE);
                    stickyListHeadersListView.setVisibility(View.GONE);
                }
                adapter.setFilter(filterList);
                adapter.notifyDataSetChanged();
            }
            else{

                tvNoRecordFound.setVisibility(View.GONE);
                stickyListHeadersListView.setVisibility(View.VISIBLE);
                adapter.setFilter(arraylist_healthBenefits);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

//fetching data from server
    private void FetchDataOfHealthBenefits() {


        StringRequest request = new StringRequest(Request.Method.POST, MyAppUrl.HEALTH_BENEFITS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response!=null){
                            mProgress.setVisibility(View.GONE);
                            layoutStickyList.setVisibility(View.VISIBLE);
                            layoutConnection.setVisibility(View.GONE);

                            try {
                                JSONObject parentJsonObject = new JSONObject(response);

                                final JSONArray parentJsonArray = parentJsonObject.getJSONArray("category");

                                for(int i=0; i< parentJsonArray.length(); i++){

                                    JSONObject childJsonObject = (JSONObject) parentJsonArray.get(i);

                                    String categoryId = childJsonObject.getString("CATEGORYID");
                                    String subCategoryId = childJsonObject.getString("SUB_CATEGORY_ID");
                                    String categoryName = childJsonObject.getString("SUB_CATE_NAME");

                                    HealthBenefitsModel healthModel = new HealthBenefitsModel();
                                    healthModel.setCategoryID(categoryId);
                                    healthModel.setCategoryName(categoryName);
                                    healthModel.setSubCategoryID(subCategoryId);

                                    arraylist_healthBenefits.add(healthModel);
                                }

                                adapter = new HealthBenefitsAdapter(Health_Benefits_Activity.this,arraylist_healthBenefits);
                                stickyListHeadersListView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("TAG","Error: "+error.getMessage());
                mProgress.setVisibility(View.GONE);

                if(error instanceof NetworkError){
                    layoutConnection.setVisibility(View.VISIBLE);
                    layoutStickyList.setVisibility(View.GONE);
                }
                else if(error instanceof NoConnectionError){
                    layoutConnection.setVisibility(View.VISIBLE);
                    layoutStickyList.setVisibility(View.GONE);
                }
            }
        });

        MySingleton.getInstance(Health_Benefits_Activity.this).addToRequestQueue(request);

    }

    //Refreshing the Layout
public void RefreshButtonHealthBenefits(View view){

    Intent intent =new Intent(this,Health_Benefits_Activity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
}
    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }
}
