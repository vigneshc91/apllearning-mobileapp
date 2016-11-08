package com.learning.apl.apllearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MaterialViewActivity extends AppCompatActivity {

    private WebView materialWebView;
    private String materialUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_view);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            materialUrl = extras.getString(AppConstants.MATERIAL_URL);
        }

        materialWebView = (WebView) findViewById(R.id.materialWebView);
        WebSettings webSettings = materialWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        materialWebView.loadUrl(materialUrl);
    }
}
