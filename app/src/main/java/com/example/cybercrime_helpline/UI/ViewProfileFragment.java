package com.example.cybercrime_helpline.UI;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cybercrime_helpline.Interfaces.InterfaceUser;
import com.example.cybercrime_helpline.Models.User;
import com.example.cybercrime_helpline.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProfileFragment extends Fragment {
    TextView name,userid;
    EditText fathername, citizenship, contact;
    EditText email, username, password;
    CircleImageView circleImageView;
    Bundle bundle= getArguments();
    public  String BASE_URL = "http://192.168.123.5:3000/";
    String uid;


    public ViewProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_profile, container, false);

        name = v.findViewById(R.id.profilename);
        userid = v.findViewById(R.id.profileid);
        fathername = v.findViewById(R.id.profilefaname);
        citizenship = v.findViewById(R.id.profilecitizenship);
        contact = v.findViewById(R.id.profilecontact);
//        circleImageView = v.findViewById(R.id.proimageview);
        email = v.findViewById(R.id.profileemail);
        username = v.findViewById(R.id.profileuname);
        password = v.findViewById(R.id.profilepw);

        uid = getArguments().getString("userid");
        Toast.makeText(getContext(), uid, Toast.LENGTH_SHORT).show();

        loadProfile();


        return v;



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void loadProfile(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceUser userInterface = retrofit.create(InterfaceUser.class);

        Call<User> userCall = userInterface.getUserProfile(uid);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Log.d("profile",response.body().getEmail());
                name.setText(response.body().getFullname());
//                userid.setText(response.body().getId());
                email.setText(response.body().getEmail_address());
                contact.setText(response.body().getNumber());
                fathername.setText(response.body().getFathername());
                citizenship.setText(response.body().getCitizenid());
                username.setText(response.body().getUsername());
                password.setText(response.body().getPassword());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

