package com.example.cybercrime_helpline.Interfaces;

import com.example.cybercrime_helpline.Models.Case;
import com.example.cybercrime_helpline.Models.ResponseImage;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfaceCase {
    @POST("cases")
    Call<Void> addcase(@Body Case cases);

    @Multipart
    @POST("upload")
    Call<ResponseImage> uploadimage(@Part MultipartBody.Part img);

    @GET("/cases")
    Call<List<Case>> getallcases();

}

