package com.example.version2myrecipe.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.version2myrecipe.R;

public class ActivityLogin extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle bundle = getIntent().getExtras();
        int data = bundle.getInt("name");
        Log.d("Login", data+"");
        email = findViewById(R.id.loginTextEmailAddress);
        password = findViewById(R.id.loginTextPassword);
        login = findViewById(R.id.login);
        imageView = findViewById(R.id.loginImageView);
    }

    public void login(View view) {
        Log.d("Login", "login was called " + email.getText() + " " + password.getText());
        if(email.getText().toString().equals("user@email.com") && password.getText().toString().equals("ILOVEAND")){
            Log.d("Login", "Login success");
            this.finish();
        }
    }
}