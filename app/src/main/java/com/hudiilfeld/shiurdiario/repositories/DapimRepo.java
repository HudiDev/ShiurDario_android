package com.hudiilfeld.shiurdiario.repositories;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.hudiilfeld.shiurdiario.adapters.DapimAdapter;
import com.hudiilfeld.shiurdiario.models.WebResponse_daf;
import com.hudiilfeld.shiurdiario.models.WebService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DapimRepo extends SuperRepo{

    @Inject
    public DapimRepo(WebService webService) {
        super(webService);
    }

    public LiveData<WebResponse_daf> getData(String currentDate) {

        final MutableLiveData<WebResponse_daf> data = new MutableLiveData<>();

        super.webService.getDapim(currentDate).enqueue(new Callback<WebResponse_daf>() {
            @Override
            public void onResponse(Call<WebResponse_daf> call, Response<WebResponse_daf> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WebResponse_daf> call, Throwable t) {
                Log.d("retrofitErr", t.getLocalizedMessage());
            }
        });

        return data;
    }
}
