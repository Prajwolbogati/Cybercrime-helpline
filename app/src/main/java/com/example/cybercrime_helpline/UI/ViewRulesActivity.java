package com.example.cybercrime_helpline.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.cybercrime_helpline.Adapter.AdapterLaw;
import com.example.cybercrime_helpline.Interfaces.InterfaceLaw;
import com.example.cybercrime_helpline.Models.Rules;
import com.example.cybercrime_helpline.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewRulesActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    List<Rules> rulesList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rules);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor proximitysensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent.values[0] < proximitysensor.getMaximumRange()) {


                    Toast.makeText(ViewRulesActivity.this,"Application Closed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewRulesActivity.this, HomeFragment.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ViewRulesActivity.this,"Far",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,proximitysensor,2*1000*1000);


        recycleView = findViewById(R.id.rulesrecycleview);



        recycleView.setLayoutManager(new GridLayoutManager(this,2));
        recycleView.addItemDecoration(new ViewRulesActivity.GridSpacingItemDecoration(2, dpToPx(3), true));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(new AdapterLaw(getApplicationContext(),rulesList));

        readRules();
    }

    private void readRules() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/rules/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceLaw Apirules = retrofit.create(InterfaceLaw.class);

        Call<List<Rules>> listCall = Apirules.getalllaws();

        listCall.enqueue(new Callback<List<Rules>>() {
            @Override
            public void onResponse(Call<List<Rules>> call, Response<List<Rules>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Rules> rulesModelList = response.body();

                recycleView.setAdapter(new AdapterLaw(getApplicationContext(), rulesModelList));
            }

            @Override
            public void onFailure(Call<List<Rules>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
