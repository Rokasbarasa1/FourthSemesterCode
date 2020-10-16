package com.example.version2myrecipe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.version2myrecipe.R;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    Switch switch1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle bundle = getIntent().getExtras();
        int data = bundle.getInt("name");
        Log.d("Login", data+"");
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.login);
        switch1 = findViewById(R.id.switch1);
        imageView = findViewById(R.id.imageView);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        imageView.setImageResource(R.drawable.trash);
                } else {
                        imageView.setImageResource(R.drawable.recipes);
                }
            }
        });
    }

    public void login(View view) {
        Log.d("Login", "login was called " + email.getText() + " " + password.getText());
        if(email.getText().toString().equals("user@email.com") && password.getText().toString().equals("ILOVEAND")){
            Log.d("Login", "Login success");
            Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT);
            login.setText(R.string.email_message);
            this.finish();
        }
    }


    public void google(View view) {
        String action = Intent.ACTION_VIEW;
        Uri uri = Uri.parse("http://www.google.com");

        Intent intent = new Intent(action,uri);
        startActivity(intent);
    }

    public void emailRokas(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rokasbarasa@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hi Mom!");
        intent.putExtra(Intent.EXTRA_TEXT, "I learned a lot in class today");
        startActivity(intent);
    }

    public void webView(View view) {
        Intent intent = new Intent(Login.this, WebViewActivity.class);
        startActivity(intent);
    }
}