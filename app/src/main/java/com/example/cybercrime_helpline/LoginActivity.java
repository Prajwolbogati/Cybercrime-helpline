package com.example.cybercrime_helpline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText logpassword;
    private Button btnlog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        logpassword = findViewById(R.id.logpassword);
        btnlog = findViewById(R.id.btnlog);

        btnlog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Objects.equals(username.getText().toString(), "admin")&& Objects.equals(logpassword.getText().toString(),"admin"))
                {
                    Toast.makeText(LoginActivity.this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();{
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                }else
                {
                    Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        Login();
            }


    private void Login(){
        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username",username.getText().toString());
        editor.putString("password",logpassword.getText().toString());
        editor.commit();

        Toast.makeText(this,"Successfully Login", Toast.LENGTH_SHORT).show();
    }
}
