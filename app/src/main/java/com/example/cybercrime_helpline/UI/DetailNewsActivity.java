package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cybercrime_helpline.R;

public class DetailNewsActivity extends AppCompatActivity {
    TextView tvnewstitle, tvnewsptime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        tvnewstitle = findViewById(R.id.tvnewstitle);
        tvnewsptime = findViewById(R.id.tvnewsptime);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String image = bundle.getString("image");
            tvnewstitle.setText(bundle.getString("title"));
            tvnewsptime.setText(bundle.getString("description"));
        }
    }
}
