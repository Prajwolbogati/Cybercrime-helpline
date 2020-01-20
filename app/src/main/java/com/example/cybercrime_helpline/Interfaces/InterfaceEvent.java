package com.example.cybercrime_helpline.Interfaces;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface InterfaceEvent {
    @POST("events")
    Call<Void> addevent(@Body Event event);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadimage(@Part MultipartBody.Part img);

    @GET("/events")
    Call<List<Event>> getallevents();
}

