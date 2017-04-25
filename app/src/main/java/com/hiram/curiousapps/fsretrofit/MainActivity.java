package com.hiram.curiousapps.fsretrofit;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String API_BASE_URL = "https://api.github.com/";

    Button btnGet, btnPost, btnList;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, SimpleRequestActivity.class);
        bundle = new Bundle();
        btnGet = (Button) findViewById(R.id.btnGet);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnList = (Button) findViewById(R.id.btnGetList);

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
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, GetListActivity.class);
                /*bundle.putString("method", "get");
                intent.putExtras(bundle);*/
                startActivity(intent);
            }
        });
    }

}
