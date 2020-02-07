package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cybercrime_helpline.R;

public class CountCaseActivity extends AppCompatActivity {
    EditText ettcase;
    Button btnupcase;
    TextView tvtotalcase;


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String EventName = "nameKey";

    private NotificationManagerCompat notificationManagerCompat;
    private Context context;

    private static final String TAG = "MyActivity";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_case);
        tvtotalcase = findViewById(R.id.tvtotalcase);
        ettcase = findViewById(R.id.ettcase);

        btnupcase = findViewById(R.id.btnupcase);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String previous = sharedpreferences.getString(EventName,"");
        tvtotalcase.setText(previous);

        btnupcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = ettcase.getText().toString();
                int yet = Integer.parseInt(name);
                String total = String.valueOf(yet);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(EventName, total);
                editor.commit();
            }
        });
    }

}