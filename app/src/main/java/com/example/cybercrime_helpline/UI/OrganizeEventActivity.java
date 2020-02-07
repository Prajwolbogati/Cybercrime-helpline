package com.example.cybercrime_helpline.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cybercrime_helpline.Interfaces.InterfaceEvent;
import com.example.cybercrime_helpline.Models.Event;
import com.example.cybercrime_helpline.Models.ResponseImage;
import com.example.cybercrime_helpline.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrganizeEventActivity extends AppCompatActivity {

    EditText eventName, eventType, eventDescription, eventlocation;
    ImageView imgevent;
    Button btnAddevent, btnsure;
    TextView eventpicname;
    Uri uri;
    Bitmap bitmap;
    Retrofit retrofit;
    InterfaceEvent interfaceevent;
    private static final int PICK_IMAGE = 1;

    public  String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize_event);

        eventName=findViewById(R.id.eventName);
        eventType=findViewById(R.id.eventType);
        eventDescription=findViewById(R.id.eventDescription);
        eventlocation=findViewById(R.id.eventlocation);
        imgevent = findViewById(R.id.imgevent);
        eventpicname=findViewById(R.id.eventpicname);
        btnAddevent =findViewById(R.id.btnAddevent);
        btnsure =findViewById(R.id.btnsure);

        imgevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });




        btnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(bitmap);
                Toast.makeText(OrganizeEventActivity.this, "Image Confirmed", Toast.LENGTH_SHORT).show();
            }
        });



        btnAddevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }

    private void BrowseImage() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
    }

    private void addImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(this.getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            RequestBody requestBody = RequestBody.
                    create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.
                    createFormData("imageFile", file.getName(), requestBody);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            interfaceevent = retrofit.create(InterfaceEvent.class);
            Call<ResponseImage> imgCall = interfaceevent.uploadimage(body);
            imgCall.enqueue(new Callback<ResponseImage>() {
                @Override
                public void onResponse(Call<ResponseImage> call, Response<ResponseImage> response) {
                    eventpicname.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<ResponseImage> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap
                        (getContentResolver(), uri);

                imgevent.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEvent(){
        if (nullValidation()) {

            String stname = eventName.getText().toString();
            String steventtype = eventType.getText().toString();
            String stimageName = eventpicname.getText().toString();
            String stdesc = eventDescription.getText().toString();
            String stlocation = eventlocation.getText().toString();

            Event event = new Event(stname,steventtype, stimageName, stdesc, stlocation);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            InterfaceEvent eventInterface = retrofit.create(InterfaceEvent.class);

            Call<Void> call = eventInterface.addevent(event);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("VAL", "success ");

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "EVENT ADDED", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

                    }else {
                        try{
                            Log.d("VAL", response.toString());

                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }
    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(eventName.getText().toString())){
            eventName.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(eventType.getText().toString())){
            eventType.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(eventDescription.getText().toString())){

            eventDescription.setError("Required Field");
            return false;
        }

        else if (TextUtils.isEmpty(eventlocation.getText().toString())){

            eventlocation.setError("Required Field");
            return false;
        }

        return true;
    }

}
