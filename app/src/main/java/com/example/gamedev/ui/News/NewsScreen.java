package com.example.gamedev.ui.News;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.gamedev.R;

public class NewsScreen extends AppCompatActivity
{
    String Url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_screen);

        Intent intent = getIntent();
        Url = intent.getStringExtra("urlName");

        WebView WebV = (WebView) findViewById(R.id.WebScreen);
        WebV.getSettings().setJavaScriptEnabled(true);
        WebV.setWebViewClient(new MyWebViewClient());
        WebV.loadUrl(Url);
    }

    private static class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
