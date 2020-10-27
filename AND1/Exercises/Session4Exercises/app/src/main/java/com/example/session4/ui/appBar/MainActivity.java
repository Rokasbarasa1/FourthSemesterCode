package com.example.session4.ui.appBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.session4.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.activity1:
                Log.d("Tools", "share activity1");
                Intent intent2 = new Intent(MainActivity.this, Activity1.class);
                startActivity(intent2);
                break;
            case R.id.activity2:
                Log.d("Tools", "share activity2");
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
                break;
            case R.id.activity3:
                Log.d("Tools", "share activity3");
                Intent intent1 = new Intent(MainActivity.this, Activity3.class);
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

    public void displayToast(View view) {
        //Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
        Snackbar snackbar = Snackbar.make(view, "clicked", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}