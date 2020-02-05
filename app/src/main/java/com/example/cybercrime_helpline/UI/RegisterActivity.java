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
    EditText regfullname, regcitizenship, regfathername, regcontact, regemail, regusername, regpassword;
    Button btnregister, btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regfullname = findViewById(R.id.regfullname);
        regcitizenship = findViewById(R.id.regcitizenship);
        regfathername = findViewById(R.id.regfathername);
        regcontact = findViewById(R.id.regcontact);
        regemail = findViewById(R.id.regemail);
        regusername = findViewById(R.id.regusername);
        regpassword = findViewById(R.id.regpassword);

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
                        regfullname.getText().toString(),
                        regcitizenship.getText().toString(),
                        regfathername.getText().toString(),
                        regcontact.getText().toString(),
                        regemail.getText().toString(),
                        regusername.getText().toString(),
                        regpassword.getText().toString()
                );

                Registerbll bll = new Registerbll();
                if(bll.registeruser(registerBLL)){
                    regfullname.setText("");
                    regfathername.setText("");
                    regemail.setText("");
                    regusername.setText("");
                    regcontact.setText("");
                    regcitizenship.setText("");
                    regpassword.setText("");
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public boolean nullValidation(){
        if (TextUtils.isEmpty(regfullname.getText().toString())){
            regfullname.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(regcitizenship.getText().toString())){
            regcitizenship.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(regfathername.getText().toString())){
            regfathername.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(regcontact.getText().toString())){
            regcontact.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(regemail.getText().toString())){
            regemail.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(regusername.getText().toString())){
            regusername.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(regpassword.getText().toString())){
           regpassword.setError("Required Field");
            return false;
        }

        return true;
    }


}

