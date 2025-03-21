package com.example.aslsignlanguage.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aslsignlanguage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayQuizActivity extends AppCompatActivity {

    private WebView webView;
    List<String> linksList = new ArrayList<>();


    @SuppressLint({"MissingInflatedId", "ObsoleteSdkInt", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.webView);

        linksList.add("https://wordwall.net/embed/a31e5d79838f4002922cda4e4d3f043b?themeId=21&templateId=69&fontStackId=0");
        linksList.add("https://wordwall.net/embed/4788128ac8124a1db9b01985604ff46a?themeId=59&templateId=69&fontStackId=0");
        linksList.add("https://wordwall.net/embed/7e77dde07a634b96b1d4d41bfdf4b73b?themeId=23&templateId=49&fontStackId=0");
        linksList.add("https://wordwall.net/embed/5c4cc5a9c7e04f8e87637347b8952b19?themeId=3&templateId=46&fontStackId=0");
        linksList.add("https://wordwall.net/embed/1ba14e1763574dd4847f151782fa0b6e?themeId=41&templateId=22&fontStackId=0");
        linksList.add("https://wordwall.net/embed/72044c353ec448f7bdeaec1f99beac59?themeId=2&templateId=25&fontStackId=0");
        linksList.add("https://wordwall.net/embed/0cf2d70e20f5475a88b10b2d233090dc?themeId=1&templateId=11&fontStackId=0");
        linksList.add("https://wordwall.net/embed/dc50a4de5d6848b6a5be09e49817d9e3?themeId=21&templateId=69&fontStackId=0");
        linksList.add("https://wordwall.net/embed/7dbd6151cf654589be41c6f39e54a5f7?themeId=2&templateId=10&fontStackId=0");
        linksList.add("https://wordwall.net/embed/4d94c2b3c546431eb2a5ae7ff9f6ed37?themeId=21&templateId=69&fontStackId=12");
        linksList.add("https://wordwall.net/embed/0667cb921d5d42749d514c710baf7022?themeId=1&templateId=46&fontStackId=0");
        linksList.add("https://wordwall.net/embed/5e3e1c6459d04590a0c5afe46338fd14?themeId=21&templateId=69&fontStackId=0");
        linksList.add("https://wordwall.net/embed/5a67df98b87f493881b31de01ac1e7a7?themeId=1&templateId=22&fontStackId=0");

        // Enable WebView debugging for development (API 19+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        WebSettings webSettings = webView.getSettings();

        // --- Essential Settings ---
        // Enable JavaScript (if needed by your content)
        webSettings.setJavaScriptEnabled(true);
        // Enable DOM storage for HTML5 features
        webSettings.setDomStorageEnabled(true);
        // Enable caching
        //webSettings.setAppCacheEnabled(true);
        //webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // --- Layout & Zoom ---
        // Adjust the view to fit the content
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        // Enable pinch-to-zoom without on-screen zoom controls
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        // --- Mixed Content (for API 21+) ---
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Allow mixed content if needed; adjust according to your security requirements
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        // --- Security ---
        // Disable file access if not required (helps reduce potential vulnerabilities)
        webSettings.setAllowFileAccess(false);
        // Enable safe browsing on API 26+
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            WebView.enableSafeBrowsing(this);
//        }

        // --- Handling Navigation ---
        // Ensure links open within the WebView rather than launching a browser
        webView.setWebViewClient(new WebViewClient() {
            // Optionally override onReceivedError, onPageFinished, etc.
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // Handle errors gracefully
                super.onReceivedError(view, request, error);
            }
        });

        // Load your desired URL
        webView.loadUrl(randomLink(linksList));



    }

    private String randomLink(List<String> list){
        if (list == null || list.isEmpty()) {
            return null; // or throw an exception
        }else{
            Random random = new Random();
            int index = random.nextInt(list.size());
            Toast.makeText(this, ""+index, Toast.LENGTH_SHORT).show();
            return list.get(index);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        webView.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlayQuizActivity.this, QuizActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

}