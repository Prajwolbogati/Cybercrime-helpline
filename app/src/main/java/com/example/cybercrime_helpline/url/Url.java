package com.example.cybercrime_helpline.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String BASE_URL = "http://192.168.123.5:3000/";
    public static String Cookie;
    public static String userid="";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
