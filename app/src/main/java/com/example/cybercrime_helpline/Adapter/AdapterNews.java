package com.example.cybercrime_helpline.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cybercrime_helpline.Models.News;
import com.example.cybercrime_helpline.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsViewHolder> {
    Context mContext;
    List<News> newsList;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public AdapterNews(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public AdapterNews.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_detail_news, viewGroup, false);
        return new AdapterNews.NewsViewHolder(view);
    }





    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNews.NewsViewHolder holder, final int i) {

        final News newsModel = newsList.get(i);

        holder.tvnewstitle.setText(newsModel.getTitle());
        holder.tvnewsptime.setText(newsModel.getDescription());

        StrictMode();
        String images = newsList.get(i).getImage_Name();
        final String path = BASE_URL + "/uploads" + "/" + images;
        Log.d("aaa", "onBindViewHolder: " + newsList.get(i).getImage_Name());
        System.out.println("Path: " + path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream) uri.getContent());
            holder.newspic1.setImageBitmap(bitmap);
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
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView newspic1;
        TextView tvnewstitle, tvnewsptime;

        public NewsViewHolder(@NonNull View newsView) {
            super(newsView);
            newspic1 = newsView.findViewById(R.id.newspic1);
            tvnewstitle = newsView.findViewById(R.id.tvnewstitle);
            tvnewsptime = newsView.findViewById(R.id.tvnewsptime);


        }
    }
}


