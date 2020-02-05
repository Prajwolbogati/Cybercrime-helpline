package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cybercrime_helpline.BLL.Loginbll;
import com.example.cybercrime_helpline.MainActivity;
import com.example.cybercrime_helpline.R;
import com.example.cybercrime_helpline.StrictMod.StrictMode;
import com.example.cybercrime_helpline.url.Url;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText logpassword;
    private Button btnlog;
    private Button btnreg;
    private boolean isSuccess = false;

    public String BASE_URL = "http://10.0.2.2:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        logpassword = findViewById(R.id.logpassword);
        btnlog = findViewById(R.id.btnlog);
        btnreg = findViewById(R.id.btnreg);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Loginbll bll = new Loginbll(username.getText().toString(), logpassword.getText().toString());
                StrictMode.StrictMode();

                if (bll.checkUser()) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("userid", Url.userid);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public boolean nullValidation(){
        if (TextUtils.isEmpty(username.getText().toString())){
            username.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(logpassword.getText().toString())){
            logpassword.setError("Required Field");
            return false;
        }
        return true;
    }

}