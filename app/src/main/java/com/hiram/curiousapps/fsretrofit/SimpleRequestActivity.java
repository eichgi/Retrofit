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

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        API client = retrofit.create(API.class);

        //Post post = new Post("Rose Rodriguez", 25, "rose@mail.com");

        Call<User> postCall = client.postData("post.php", name, age, email);

        Log.v(getApplicationContext().toString(), "PC:" + postCall.request().body());

        postCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> postCall, Response<User> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it

                User custom = response.body();
                String res = "Method: " + method;
                res += "\nName: " + custom.getNombre() + " \nAge: " + custom.getEdad() + " \nEmail: " + custom.getEmail();
                etResponse.setText(res);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v(getApplicationContext().toString(), t.getMessage());
            }
        });
    }

    private void makeGetRequest() {

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
        API client = retrofit.create(API.class);

        // Fetch a list of the Github repositories.
        Call<User> call =
                client.getData(name, age, email);
        //client.getData("nested.json");

        Log.v(getApplicationContext().toString(), "GC:" + call.request().toString());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it

                User custom = response.body();
                String res = "Method: " + method;
                res += "\nName: " + custom.getNombre() + " \nAge: " + custom.getEdad() + " \nEmail: " + custom.getEmail();
                etResponse.setText(res);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Toast.makeText(SimpleRequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v(getApplicationContext().toString(), t.getMessage());
            }
        });
    }
}

