package com.hiram.curiousapps.fsretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hiram.curiousapps.fsretrofit.interfaces.API;
import com.hiram.curiousapps.fsretrofit.models.User;

import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SimpleRequestActivity extends AppCompatActivity {

    Button btnRequest;
    EditText etName, etAge, etEmail, etResponse;
    String API_URL = "http://api.eichgi.com";
    Intent intent;
    Bundle bundle;
    String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_request);

        intent = getIntent();
        bundle = intent.getExtras();
        method = bundle.getString("method");

        btnRequest = (Button) findViewById(R.id.btnRequest);
        btnRequest.setText("Send " + method + " request");
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etResponse = (EditText) findViewById(R.id.etResponse);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(method, "get")) {
                    makeGetRequest();
                } else {
                    makePostRequest();
                }
            }
        });
    }

    private void makePostRequest() {
        String name = etName.getText() + "";
        int age = Integer.parseInt(etAge.getText() + "");
        String email = etEmail.getText() + "";

        /** We start to build our retrofit instance builder passing the URL BASE and optionally
         * the factory we want to use for deserialization */
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create());

        /** Then we can assing an already builded instance */
        Retrofit retrofit = builder.build();

        /** This is how we tell our retrofit's instance how to manage the selected interface*/
        API client = retrofit.create(API.class);

        /** Now we define the instance of the call with our model's type, now client can call its own methods */
        Call<User> postCall = client.postData("post.php", name, age, email);

        /** Then we enqueue the callback with our Class type, both method onResponse
         * and onFailure will be able to receive the response, this call is asynchronous */
        postCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> postCall, Response<User> response) {
                /** We need to pour the response over an instance of the same type than our Call's type */
                User custom = response.body();
                String res = "Method: " + method;
                res += "\nName: " + custom.getNombre() + " \nAge: " + custom.getEdad() + " \nEmail: " + custom.getEmail();
                etResponse.setText(res);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                /** If there's an error we toast it*/
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeGetRequest() {

        String name = etName.getText() + "";
        int age = Integer.parseInt(etAge.getText() + "");
        String email = etEmail.getText() + "";

        /** You don't need to define the http client, retrofit do it if you don't*/
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit =
                builder
                        //.client(httpClient.build())
                        .build();

        API client = retrofit.create(API.class);

        Call<User> call = client.getData(name, age, email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User custom = response.body();
                String res = "Method: " + method;
                res += "\nName: " + custom.getNombre() + " \nAge: " + custom.getEdad() + " \nEmail: " + custom.getEmail();
                etResponse.setText(res);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SimpleRequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v(getApplicationContext().toString(), t.getMessage());
            }
        });
    }
}

