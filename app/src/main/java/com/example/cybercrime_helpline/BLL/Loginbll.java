package com.example.cybercrime_helpline.BLL;

import com.example.cybercrime_helpline.Interfaces.InterfaceUser;
import com.example.cybercrime_helpline.Models.RegisterLoginResponse;
import com.example.cybercrime_helpline.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Loginbll {
    private String username;
    private String password;
    boolean inSuccess = false;

    public Loginbll(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkUser(){
        InterfaceUser userInterface = Url.getInstance().create(InterfaceUser.class);
        Call<RegisterLoginResponse> usersCall = userInterface.checkUser(username,password);

        try {
            Response<RegisterLoginResponse> imageResponseResponse = usersCall.execute();
            if(imageResponseResponse.body().getSuccess()){
                Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                Url.userid = imageResponseResponse.body().getId();
                inSuccess = true;
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return inSuccess;
    }

}


