package com.hudiilfeld.shiurdiario.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.Utils;
import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.models.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaunchActivity extends AppCompatActivity {

    ProgressBar progressBar;

    public static final String PREFIX = "prefix";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        progressBar = findViewById(R.id.videoProgressBar);


        Retrofit.Builder builder = new Builder()
                .baseUrl("http://ws.shiurdiario.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WebService client = retrofit.create(WebService.class);

        Call<WebResponse_masechet> call = client.getMasechtot(Utils.getCurrentDate());

        call.enqueue(new Callback<WebResponse_masechet>() {
            @Override
            public void onResponse(Call<WebResponse_masechet> call, Response<WebResponse_masechet> response) {
                WebResponse_masechet jsonData = response.body();
                progressBar.setVisibility(View.INVISIBLE);
                String prefix = jsonData.getD().getPrefix();
                Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
                intent.putExtra(PREFIX, prefix);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<WebResponse_masechet> call, Throwable t) {

            }
        });
    }
}
