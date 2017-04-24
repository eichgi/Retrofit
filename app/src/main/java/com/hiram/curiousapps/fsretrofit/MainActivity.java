package com.hiram.curiousapps.fsretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hiram.curiousapps.fsretrofit.interfaces.CustomClient;
import com.hiram.curiousapps.fsretrofit.interfaces.GitHubClient;
import com.hiram.curiousapps.fsretrofit.modelos.Post;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    String API_BASE_URL = "https://api.github.com/";

    Button btnGet, btnPost;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, simpleRequestActivity.class);
        bundle = new Bundle();
        btnGet = (Button) findViewById(R.id.btnGet);
        btnPost = (Button) findViewById(R.id.btnPost);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("method", "get");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("method", "post");
                intent.putExtras(bundle);
                startActivity(intent);
                //postCall();
            }
        });
    }

}
