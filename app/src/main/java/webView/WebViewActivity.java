package webView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.codec.healthapp.MainActivity;
import com.codec.healthapp.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar mProgress;
    String URL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.webView);
        mProgress = findViewById(R.id.progressbar_webView);

        Intent intent = getIntent();
        URL = intent.getStringExtra("url");

        WebSettings webSettings = mWebView.getSettings();

        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setHorizontalScrollBarEnabled(false);

        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(URL);

    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }
        else{
            super.onBackPressed();
        }

    }

    //back button click listiner
    public void GoBackbtn(View view){
        Intent backIntent = new Intent(this, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backIntent);
        finish();
    }

    class MyWebViewClient extends WebViewClient{
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            mProgress.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }
    }
}
