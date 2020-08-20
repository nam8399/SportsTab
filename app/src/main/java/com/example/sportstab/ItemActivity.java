package com.example.sportstab;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        String link = intent.getStringExtra("Link");

        wv=findViewById(R.id.wv);

        wv.getSettings().setJavaScriptEnabled(true);

        wv.setWebViewClient(new WebViewClient());

        wv.setWebChromeClient(new WebChromeClient());

        wv.loadUrl(link);
    }
}
