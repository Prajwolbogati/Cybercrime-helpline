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

import com.example.cybercrime_helpline.Interfaces.InterfaceCase;
import com.example.cybercrime_helpline.Models.Case;
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

public class FileCaseActivity extends AppCompatActivity {
    public EditText victimname, etcriminal, etcasetype, etevidence, etdescription, etlocation;
    ImageView casepic;
    public Button btnfilecase, btnverrify;
    TextView tvcasepicname;
    Uri uri;
    Bitmap bitmap;
    Retrofit retrofit;
    InterfaceCase itemInterface;
    private static final int PICK_IMAGE = 1;

    public  String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_case);
        victimname = findViewById(R.id.victimname);
        etcriminal = findViewById(R.id.etcriminal);
        etcasetype = findViewById(R.id.etcasetype);
        etevidence = findViewById(R.id.etevidence);
        casepic = findViewById(R.id.casepic);
        etdescription = findViewById(R.id.etdescription);
        etlocation = findViewById(R.id.etlocation);

        tvcasepicname = findViewById(R.id.tvcasepicname);

        tvcasepicname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });


        btnverrify = findViewById(R.id.btnverrify);
        btnverrify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(bitmap);
                Toast.makeText(FileCaseActivity.this, "Image Confirmed", Toast.LENGTH_SHORT).show();
            }
        });

        btnfilecase = findViewById(R.id.btnfilecase);

        btnfilecase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCase();
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

            itemInterface = retrofit.create(InterfaceCase.class);
            Call<ResponseImage> imgCall = itemInterface.uploadimage(body);
            imgCall.enqueue(new Callback<ResponseImage>() {
                @Override
                public void onResponse(Call<ResponseImage> call, Response<ResponseImage> response) {
                    tvcasepicname.setText(response.body().getFilename());
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

                casepic.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCase(){
        if (nullValidation()) {

            String stname = victimname.getText().toString();
            String stcriminal = etcriminal.getText().toString();
            String stcasetype = etcasetype.getText().toString();
            String stevidence = etevidence.getText().toString();
            String stimageName = tvcasepicname.getText().toString();
            String stdesc = etdescription.getText().toString();
            String stlocation = etlocation.getText().toString();

            Case cases = new Case(stname, stcriminal, stcasetype, stevidence, stimageName,stdesc, stlocation);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            InterfaceCase CaseInterface = retrofit.create(InterfaceCase.class);

            Call<Void> call = CaseInterface.addcase(cases);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("VAL", "success ");

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "CASE ADDED", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

                        Intent  caseintent = new Intent(FileCaseActivity.this, CasesFragment.class);
                        startActivity(caseintent);
                        finish();

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
        if (TextUtils.isEmpty(victimname.getText().toString())){
            victimname.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(etcriminal.getText().toString())){
            etcriminal.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(etcasetype.getText().toString())){
            etcasetype.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(etevidence.getText().toString())){
            etevidence.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(etdescription.getText().toString())){

            etdescription.setError("Required Field");
            return false;
        }

        else if (TextUtils.isEmpty(etlocation.getText().toString())){

            etlocation.setError("Required Field");
            return false;
        }

        return true;
    }

}
