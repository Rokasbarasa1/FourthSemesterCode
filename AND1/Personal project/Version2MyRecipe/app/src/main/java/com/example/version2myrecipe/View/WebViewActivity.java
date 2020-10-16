package com.example.version2myrecipe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import com.example.version2myrecipe.R;

public class WebViewActivity extends AppCompatActivity {

    EditText url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url = findViewById(R.id.editTextTextPersonName);
        webView = findViewById(R.id.webView);
    }

    public void loadWebSite(View view) {
        //String action = Intent.ACTION_VIEW;
        //Uri uri = Uri.parse(url.getText().toString());
        webView.loadUrl(url.getText().toString());
        //Intent intent = new Intent(action,uri);
        //startActivity(intent);
    }
}