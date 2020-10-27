package com.example.session4.ui.appBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.session4.R;

public class Activity3 extends MainActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Activity3");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.activity1:
                Log.d("Tools", "share activity1");
                Intent intent2 = new Intent(Activity3.this, Activity1.class);
                startActivity(intent2);
                break;
            case R.id.activity2:
                Log.d("Tools", "share activity2");
                Intent intent = new Intent(Activity3.this, Activity2.class);
                startActivity(intent);
                break;
            case R.id.activity3:
                Log.d("Tools", "share activity3");
                break;
            case R.id.action_settings:
                Log.d("Tools", "settings clicked");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}