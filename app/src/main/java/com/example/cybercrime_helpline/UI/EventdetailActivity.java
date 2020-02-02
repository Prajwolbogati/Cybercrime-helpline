package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cybercrime_helpline.R;
import com.squareup.picasso.Picasso;


public class EventdetailActivity extends AppCompatActivity {

    TextView eventdetailName, detaileventtype, detaileDescription, detailevLocation;
    ImageView eventdetailpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetail);

        eventdetailName = findViewById(R.id.victimdetailName);
        detaileventtype = findViewById(R.id.detaileventtype);
        detaileDescription = findViewById(R.id.detailcDescription);
        detailevLocation = findViewById(R.id.detailcaLocation);
        eventdetailpic = findViewById(R.id.eventdetailpic);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String image = bundle.getString("image");
            Picasso.with(this).load("http://10.0.2.2:3000/uploads/" + image).into(eventdetailpic);
            eventdetailName.setText(bundle.getString("fullname"));
            detaileventtype.setText(bundle.getString("event_type"));
            detaileDescription.setText(bundle.getString("description"));
            detailevLocation.setText(bundle.getString("area"));
        }
    }
}
