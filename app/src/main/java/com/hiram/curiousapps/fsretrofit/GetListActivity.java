package com.hiram.curiousapps.fsretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hiram.curiousapps.fsretrofit.interfaces.API;
import com.hiram.curiousapps.fsretrofit.models.Language;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetListActivity extends AppCompatActivity {

    String API_URL = "http://api.eichgi.com";
    Button btnGetList;
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list);

        tvResponse = (TextView) findViewById(R.id.tvResponse);
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

        API client = retrofit.create(API.class);

        Call<List<Language>> listCall = client.getList();

        listCall.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                List<Language> custom = response.body();
                String res = "";
                for (Language language : custom) {
                    res += language.allData() + "\n";
                }
                tvResponse.setText(res);
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {
                Log.e(getApplicationContext().toString(), t.getMessage());
            }
        });

    }

}
