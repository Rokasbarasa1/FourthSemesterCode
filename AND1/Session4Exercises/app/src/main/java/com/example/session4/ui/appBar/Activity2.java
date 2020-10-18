package com.example.session4.ui.appBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.session4.R;

public class Activity2 extends MainActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Activity2");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.activity1:
                Log.d("Tools", "share activity1");
                Intent intent2 = new Intent(Activity2.this, Activity1.class);
                startActivity(intent2);
                break;
            case R.id.activity2:
                Log.d("Tools", "share activity2");
                break;
            case R.id.activity3:
                Log.d("Tools", "share activity3");
                Intent intent1 = new Intent(Activity2.this, Activity3.class);
                startActivity(intent1);
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