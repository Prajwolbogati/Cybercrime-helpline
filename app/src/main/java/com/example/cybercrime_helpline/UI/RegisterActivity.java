package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cybercrime_helpline.BLL.Registerbll;
import com.example.cybercrime_helpline.Models.User;
import com.example.cybercrime_helpline.R;
import com.example.cybercrime_helpline.StrictMod.StrictMode;

public class RegisterActivity extends AppCompatActivity {
    EditText fullname, citizenid, fathername, number, email_address, username, password;
    Button btnregister, btnlogin;

    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.regfullname);
        citizenid = findViewById(R.id.regcitizenship);
        fathername = findViewById(R.id.regfathername);
        number = findViewById(R.id.regcontact);
        email_address = findViewById(R.id.regemail);
        username = findViewById(R.id.regusername);
        password = findViewById(R.id.regpassword);

        btnregister = findViewById(R.id.btnregister);

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);

            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.StrictMode();

                User registerBLL = new User(
                        fullname.getText().toString(),
                        citizenid.getText().toString(),
                        fathername.getText().toString(),
                        number.getText().toString(),
                        email_address.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString()
                );

                Registerbll bll = new Registerbll();
                if(bll.registeruser(registerBLL)){
                    fullname.setText("");
                    fathername.setText("");
                    email_address.setText("");
                    username.setText("");
                    number.setText("");
                    citizenid.setText("");
                    password.setText("");
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public boolean nullValidation(){
        if (TextUtils.isEmpty(fullname.getText().toString())){
            fullname.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(citizenid.getText().toString())){
            citizenid.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(fathername.getText().toString())){
            fathername.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(number.getText().toString())){
            number.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(email_address.getText().toString())){
            email_address.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(username.getText().toString())){
            username.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
           password.setError("Required Field");
            return false;
        }

        return true;
    }


}

