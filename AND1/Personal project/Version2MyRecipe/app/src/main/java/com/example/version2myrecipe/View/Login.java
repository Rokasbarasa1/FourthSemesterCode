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
        imageView = findViewById(R.id.imageView);
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
}