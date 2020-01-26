package com.example.cybercrime_helpline.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cybercrime_helpline.Models.Rules;
import com.example.cybercrime_helpline.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterLaw extends RecyclerView.Adapter<AdapterLaw.RulesViewHolder> {
    Context mContext;
    List<Rules> rulesList;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public AdapterLaw(Context mContext, List<Rules> rulesList) {
        this.mContext = mContext;
        this.rulesList = rulesList;
    }

    @NonNull
    @Override
    public AdapterLaw.RulesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_detail_laws, viewGroup, false);
        return new AdapterLaw.RulesViewHolder(view);
    }





    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLaw.RulesViewHolder holder, final int i) {

        final Rules rulesModel = rulesList.get(i);

        holder.lawsdetailtitle.setText(rulesModel.getTitle());
        holder.desc.setText(rulesModel.getDescription());
        holder.tvlawsfines.setText(rulesModel.getFine());

        StrictMode();
        String images = rulesList.get(i).getImage_Name();
        final String path = BASE_URL + "/uploads" + "/" + images;
        Log.d("aaa", "onBindViewHolder: " + rulesList.get(i).getImage_Name());
        System.out.println("Path: " + path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream) uri.getContent());
            holder.imglawsview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        rulesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(mContext, RulesDetailsActivity.class);
//                intent.putExtra("image", rulesModel.getImageName());
//                intent.putExtra("title", rulesModel.getTitle());
//                intent.putExtra("desc", rulesModel.getDesc());
//                intent.putExtra("fines, rulesModel.getFines());
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return rulesList.size();
    }

    public class RulesViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imglawsview;
        TextView lawsdetailtitle, desc, tvlawsfines;

        public RulesViewHolder(@NonNull View rulesView) {
            super(rulesView);
            imglawsview = rulesView.findViewById(R.id.imglawsview);
            lawsdetailtitle = rulesView.findViewById(R.id.lawsdetailtitle);
            desc = rulesView.findViewById(R.id.desc);
            tvlawsfines = rulesView.findViewById(R.id.tvlawsfines);

        }
    }
}

