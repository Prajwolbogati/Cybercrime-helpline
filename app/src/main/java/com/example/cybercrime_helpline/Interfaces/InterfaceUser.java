package com.example.cybercrime_helpline.Interfaces;

import com.example.cybercrime_helpline.Models.RegisterLoginResponse;
import com.example.cybercrime_helpline.Models.RegisterResponse;
import com.example.cybercrime_helpline.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceUser {
    @POST("users/signup")
    Call<RegisterResponse> registeruser(
            @Body User user
    );


    @FormUrlEncoded
    @POST("users/login")
    Call<RegisterLoginResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @GET("/users/{id}")
    Call<User> getUserProfile(@Path("id") String id);

    @GET("users/logout")
    Call<Void> logout(@Header("Cookie") String cookie);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);
}

