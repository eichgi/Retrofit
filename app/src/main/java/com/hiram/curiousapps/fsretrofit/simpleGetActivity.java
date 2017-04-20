package com.hiram.curiousapps.fsretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hiram.curiousapps.fsretrofit.interfaces.CustomClient;
import com.hiram.curiousapps.fsretrofit.modelos.Get;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class simpleGetActivity extends AppCompatActivity {

    Button btnRequest;
    EditText etName, etAge, etEmail, etResponse;
    String API_URL = "http://api.eichgi.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_get);

        btnRequest = (Button) findViewById(R.id.btnRequest);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etResponse = (EditText) findViewById(R.id.etResponse);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });
    }

    private void makeRequest() {

        String name = etName.getText() + "";
        int age = Integer.parseInt(etAge.getText() + "");
        String email = etEmail.getText() + "";
        //The client is optional, retrofit creates it if not defined
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit =
                builder
                        //.client(httpClient.build())
                        .build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        CustomClient client = retrofit.create(CustomClient.class);

        // Fetch a list of the Github repositories.
        Call<Get> call =
                client.getData(name, age, email);
        //client.getData("nested.json");

        Log.v(getApplicationContext().toString(), "GC:" + call.request().toString());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Get>() {
            @Override
            public void onResponse(Call<Get> call, Response<Get> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it

                Get custom = response.body();
                String res = "Name: " + custom.getNombre() + " \nAge: " + custom.getEdad() + " \nEmail: " + custom.getEmail();
                Toast.makeText(simpleGetActivity.this, res, Toast.LENGTH_SHORT).show();
                etResponse.setText(res);
            }

            @Override
            public void onFailure(Call<Get> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Toast.makeText(simpleGetActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v(getApplicationContext().toString(), t.getMessage());
            }
        });
    }
}

