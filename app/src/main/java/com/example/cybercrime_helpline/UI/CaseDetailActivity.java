package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cybercrime_helpline.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CaseDetailActivity extends AppCompatActivity {
    CircleImageView caseimgview;
    TextView casename, cybercriminal, casevtype, tvcevidence, tvcdesc, tvclocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);

        caseimgview=findViewById(R.id.caseimgview);
        casename=findViewById(R.id.casename);
        cybercriminal=findViewById(R.id.cybercriminal);
        casevtype=findViewById(R.id.casevtype);
        tvcevidence=findViewById(R.id.tvcevidence);
        tvcdesc=findViewById(R.id.tvcdesc);
        tvclocation=findViewById(R.id.tvclocation);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String image = bundle.getString("image");
            Picasso.with(this).load("http://10.0.2.2:3000/uploads/" + image).into(caseimgview);
            casename.setText(bundle.getString("fullname"));
            casevtype.setText(bundle.getString("case_type"));
            tvcdesc.setText(bundle.getString("description"));
            tvclocation.setText(bundle.getString("area"));
        }
    }
}
