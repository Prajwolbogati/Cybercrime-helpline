package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cybercrime_helpline.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailLawsActivity extends AppCompatActivity {

    TextView lawsdetailtitle, desc, tvlawsfines;
    CircleImageView imglawsview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laws);
        lawsdetailtitle = findViewById(R.id.lawsdetailtitle);
        desc = findViewById(R.id.desc);
        tvlawsfines = findViewById(R.id.tvlawsfines);
        imglawsview = findViewById(R.id.imglawsview);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String image = bundle.getString("image");
             Picasso.with(this).load("http://10.0.2.2:3000/uploads/" + image).into(imglawsview);
            lawsdetailtitle.setText(bundle.getString("title"));
            desc.setText(bundle.getString("description"));
            tvlawsfines.setText(bundle.getString("fine"));
        }
    }
}