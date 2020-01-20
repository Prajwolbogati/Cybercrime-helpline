package com.example.cybercrime_helpline.Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.cybercrime_helpline.Models.Event;
import com.example.cybercrime_helpline.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.EventViewHolder> {
    Context mContext;
    List<Event> Listevent;
    //    Event eventModel;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public AdapterEvent(Context mContext, List<Event> eventList) {
        this.mContext = mContext;
        this.Listevent = eventList;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_event,viewGroup,false);
        return new EventViewHolder(view);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, final int i) {

        final Event eventModel = Listevent.get(i);


        eventViewHolder.eventname.setText(eventModel.getFullname());
        eventViewHolder.eventvtype.setText(eventModel.getEvent_type());
        eventViewHolder.tvedesc.setText(eventModel.getDescription());
        eventViewHolder.tvelocation.setText(eventModel.getArea());

        StrictMode();
        String images = Listevent.get(i).getImage_Name();
        final String path = BASE_URL+"/uploads"+"/"+images;
        Log.d("load", "onBindViewHolder: " + Listevent.get(i).getImage_Name());
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            eventViewHolder.eventimgview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, EventDetailsActivity.class);
                intent.putExtra("image", eventModel.getImage_Name());
                intent.putExtra("fullname", eventModel.getFullname());
                intent.putExtra("event_type", eventModel.getEvent_type());
                intent.putExtra("description", eventModel.getDescription());
                intent.putExtra("area", eventModel.getArea());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return Listevent.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        CircleImageView eventimgview;
        TextView eventname, eventvtype, tvedesc, tvelocation;

        public EventViewHolder(@NonNull View eventView) {
            super(eventView);
            eventimgview=eventView.findViewById(R.id.eventimgview);
            eventname=eventView.findViewById(R.id.eventname);
            eventvtype=eventView.findViewById(R.id.eventvtype);
            tvedesc=eventView.findViewById(R.id.tvedesc);
            tvelocation=eventView.findViewById(R.id.tvelocation);
        }
    }
}
