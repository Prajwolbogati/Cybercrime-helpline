package com.example.cybercrime_helpline.Interfaces;

import com.example.cybercrime_helpline.Models.News;
import com.example.cybercrime_helpline.Models.ResponseImage;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfaceNews {
    @Multipart
    @POST("upload")
    Call<ResponseImage> UploadImage(@Part MultipartBody.Part img);

    @GET("/news")
    Call<List<News>> getallnews();
}
