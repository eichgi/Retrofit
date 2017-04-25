package com.hiram.curiousapps.fsretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hiram.curiousapps.fsretrofit.interfaces.CustomClient;
import com.hiram.curiousapps.fsretrofit.modelos.Language;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetListActivity extends AppCompatActivity {

    String API_URL = "http://api.eichgi.com";
    Button btnGetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list);

        btnGetList = (Button) findViewById(R.id.btnGetList);
        btnGetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getList();
            }
        });
    }

    private void getList() {

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        CustomClient client = retrofit.create(CustomClient.class);

        Call<List<Language>> listCall = client.getList();

        listCall.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {

                List<Language> custom = response.body();
                //Toast.makeText(GetListActivity.this, custom.get(0).getName(), Toast.LENGTH_SHORT).show();
                for (Language language : custom) {
                    Toast.makeText(GetListActivity.this, language.getName(), Toast.LENGTH_SHORT).show();
                    Log.d(getApplicationContext().toString(), language.getName());
                }
                //Log.d(String.valueOf(getApplicationContext()), "RES: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {
                Log.e(getApplicationContext().toString(), t.getMessage());
            }
        });

    }

}
