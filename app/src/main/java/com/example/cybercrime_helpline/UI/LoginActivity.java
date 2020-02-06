package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
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

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    private EditText username;
    private EditText logpassword;
    private Button btnlog;
    private Button btnreg;
    private boolean isSuccess = false;


    private NotificationManagerCompat notificationManagerCompat;
    private SensorManager sensorManager;
    private boolean isMoved = false;
    private View view;
    private long lastUpdate;

    public String BASE_URL = "http://10.0.2.2:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        logpassword = findViewById(R.id.logpassword);
        btnlog = findViewById(R.id.btnlog);
        btnreg = findViewById(R.id.btnreg);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();


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
                    notificationManagerCompat = NotificationManagerCompat.from(LoginActivity.this);
                    CreateNotification channel = new CreateNotification(LoginActivity.this);
                    channel.createChannel();

                    Notification notification = new NotificationCompat.Builder(LoginActivity.this, CreateNotification.CHANNEL_1)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Logged In")
                            .setContentText("Logged to CyberCrime Report")
                            .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                            .build();
                    notificationManagerCompat.notify(1, notification);

                    Vibrator vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);


                    if (Build.VERSION.SDK_INT >= 26) {
                        vb.vibrate(VibrationEffect.createOneShot(900, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vb.vibrate(200);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Username and Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();
//        Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+
//                SensorManager.GRAVITY_EARTH, Toast.LENGTH_SHORT).show();

        if (accelationSquareRoot >= 2) //it will be executed if you shuffle
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle
            if (isMoved) {
                // view.setBackgroundColor(Color.GREEN);
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
                Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();

            } else {
                // view.setBackgroundColor(Color.RED);

                Toast.makeText(LoginActivity.this,"Application Closed",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);

            }
            isMoved = !isMoved;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener((SensorEventListener) this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
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