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

import com.example.cybercrime_helpline.Models.Case;
import com.example.cybercrime_helpline.R;
import com.example.cybercrime_helpline.UI.CaseDetailActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCase  extends RecyclerView.Adapter<AdapterCase.CaseViewHolder> {
    Context mContext;
    List<Case> caseList;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public AdapterCase(Context mContext, List<Case> caseList) {
        this.mContext = mContext;
        this.caseList = caseList;
    }
    @NonNull
    @Override
    public AdapterCase.CaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_case,viewGroup,false);
        return new AdapterCase.CaseViewHolder(view);
    }





    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


        @Override
        public void onBindViewHolder(@NonNull AdapterCase.CaseViewHolder holder, final int i) {
        final Case caseModel = caseList.get(i);

        holder.casename.setText(caseModel.getFullname());
        holder.cybercriminal.setText(caseModel.getCriminal());
        holder.casevtype.setText(caseModel.getCase_type());
        holder.tvcevidence.setText(caseModel.getEvidence());
        holder.tvcdesc.setText(caseModel.getDescription());
        holder.tvclocation.setText(caseModel.getArea());

        StrictMode();
        String images = caseList.get(i).getImage_Name();
        final String path = BASE_URL+"/uploads"+"/"+images;
        Log.d("aaa", "onBindViewHolder: " + caseList.get(i).getImage_Name());
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            holder.caseimgview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, CaseDetailActivity.class);
                intent.putExtra("image", caseModel.getImage_Name());
                intent.putExtra("name", caseModel.getFullname());
                intent.putExtra("casetype", caseModel.getCase_type());
                intent.putExtra("evidence", caseModel.getEvidence());
                intent.putExtra("desc", caseModel.getDescription());
                intent.putExtra("location", caseModel.getArea());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return caseList.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder{

        CircleImageView caseimgview;
        TextView casename, cybercriminal, casevtype, tvcevidence, tvcdesc, tvclocation;

        public CaseViewHolder(@NonNull View caseView) {
            super(caseView);
            caseimgview=caseView.findViewById(R.id.caseimgview);
            casename=caseView.findViewById(R.id.casename);
            cybercriminal=caseView.findViewById(R.id.cybercriminal);
            casevtype=caseView.findViewById(R.id.casevtype);
            tvcevidence=caseView.findViewById(R.id.tvcevidence);
            tvcdesc=caseView.findViewById(R.id.tvcdesc);
            tvclocation=caseView.findViewById(R.id.tvclocation);
        }
    }
}


