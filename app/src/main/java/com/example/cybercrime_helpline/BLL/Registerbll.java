package com.example.cybercrime_helpline.BLL;

import com.example.cybercrime_helpline.Interfaces.InterfaceUser;
import com.example.cybercrime_helpline.Models.RegisterResponse;
import com.example.cybercrime_helpline.Models.User;
import com.example.cybercrime_helpline.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Registerbll {
    private String id;
    private String fullname;
    private String citizenid;
    private String fathername;
    private String number;
    private String email_address;
    private String username;
    private String password;
    boolean isSuccess = false;

    public Registerbll(String fullname, String citizenid, String fathername, String number, String email_address, String username, String password) {
        this.fullname = fullname;
        this.citizenid = citizenid;
        this.fathername = fathername;
        this.number = number;
        this.email_address = email_address;
        this.username = username;
        this.password = password;
    }
    public Registerbll()
    {

    }


    public boolean registeruser(User user) {

        InterfaceUser userInterface = Url.getInstance().create(InterfaceUser.class);
        Call<RegisterResponse> userCall = userInterface.registeruser(user);

        try {
            Response<RegisterResponse> imageResponseResponse = userCall.execute();
            if(imageResponseResponse.isSuccessful())
            {
                isSuccess = true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}

