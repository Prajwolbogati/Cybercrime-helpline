package com.example.cybercrime_helpline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private EditText username;
    private EditText logpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        },2000);
    }

    private void checkUser(){

        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        String usernames = sharedPreferences.getString("Username","");
        String password = sharedPreferences.getString("password","");
        if (usernames.equals("admin") && password.equals("admin")){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(SplashActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(SplashActivity.this,"Either username or password is incorrect",Toast.LENGTH_SHORT).show();
        }
    }
}
