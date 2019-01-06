package com.hudiilfeld.shiurdiario.repositories;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.hudiilfeld.shiurdiario.models.WebResponse_previousDaf;
import com.hudiilfeld.shiurdiario.models.WebResponse_shiurDaf;
import com.hudiilfeld.shiurdiario.models.WebService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DapimRepo extends SuperRepo{

    MutableLiveData<WebResponse_shiurDaf> mShiurDapimData;

    @Inject
    public DapimRepo(WebService webService) {
        super(webService);
    }

    public void initData() {
        mShiurDapimData = new MutableLiveData<>();
    }



    public LiveData<WebResponse_previousDaf> getPreviousDapimData(String currentDate) {

        final MutableLiveData<WebResponse_previousDaf> data = new MutableLiveData<>();

        super.webService.getPreviousDapim(currentDate).enqueue(new Callback<WebResponse_previousDaf>() {
            @Override
            public void onResponse(Call<WebResponse_previousDaf> call, Response<WebResponse_previousDaf> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WebResponse_previousDaf> call, Throwable t) {
                Log.d("retrofitErr", t.getLocalizedMessage());
            }
        });

        return data;
    }

    public MutableLiveData<WebResponse_shiurDaf> getShiurDapim(String masechet, int page) {

        Integer optionalPage = page > 0 ? page : null;

        super.webService.getShiurDapim(masechet, optionalPage).enqueue(new Callback<WebResponse_shiurDaf>() {
            @Override
            public void onResponse(Call<WebResponse_shiurDaf> call, Response<WebResponse_shiurDaf> response) {
                mShiurDapimData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WebResponse_shiurDaf> call, Throwable t) {
                Log.d("getShiurDapimErr", t.getLocalizedMessage());
            }
        });

        return mShiurDapimData;
    }
}
