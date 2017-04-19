package com.hiram.curiousapps.fsretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hiram.curiousapps.fsretrofit.modelos.Get;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    String API_BASE_URL = "https://api.github.com/";
    String API_CUSTOM_URL = "http://api.eichgi.com/";

    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = (Button) findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customCall();
            }
        });

        //gitHubCall();

    }

    public void gitHubCall() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        GitHubClient client = retrofit.create(GitHubClient.class);

        // Fetch a list of the Github repositories.
        Call<List<GitHubRepo>> call =
                client.reposForUser("eichgi");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it

                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.body().size(); i++) {
                    Log.d(getApplicationContext().toString(), response.body().get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
            }
        });
    }

    public void customCall() {
        //The client is optional, retrofit creates it if not defined
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_CUSTOM_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit =
                builder
                        //.client(httpClient.build())
                        .build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        CustomClient client = retrofit.create(CustomClient.class);

        // Fetch a list of the Github repositories.
        Call<Get> call =
                client.getData("Hiram Guerrero", 26, "ISC");
        //client.getData("nested.json");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Get>() {
            @Override
            public void onResponse(Call<Get> call, Response<Get> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it

                Get custom = response.body();
                String toast = "Nombre: " + custom.getNombre() + " \nEdad: " + custom.getEdad() + " \nProfesión: " + custom.getProfesion();
                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
                //Log.d(getApplicationContext().toString(), custom.getLenguajes().get(0).getInfo());
            }

            @Override
            public void onFailure(Call<Get> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v(getApplicationContext().toString(), t.getMessage());
            }
        });
    }
}
