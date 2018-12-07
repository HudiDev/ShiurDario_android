package com.hudiilfeld.shiurdiario.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.models.WebService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasechtotRepo extends SuperRepo{


    @Inject
    public MasechtotRepo(WebService webService) {
        super(webService);
    }

    public LiveData<WebResponse_masechet> getData(String currentDate) {

        final MutableLiveData<WebResponse_masechet> data = new MutableLiveData<>();

        super.webService.getMasechtot(currentDate).enqueue(new Callback<WebResponse_masechet>() {
            @Override
            public void onResponse(Call<WebResponse_masechet> call, Response<WebResponse_masechet> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WebResponse_masechet> call, Throwable t) {
                Log.d("retorfitErr", t.getLocalizedMessage());
            }
        });

        return data;
    }
}
