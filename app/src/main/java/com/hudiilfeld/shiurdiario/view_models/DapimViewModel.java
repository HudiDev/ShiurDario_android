package com.hudiilfeld.shiurdiario.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hudiilfeld.shiurdiario.models.Daf;
import com.hudiilfeld.shiurdiario.models.WebResponse_previousDaf;
import com.hudiilfeld.shiurdiario.models.WebResponse_shiurDaf;
import com.hudiilfeld.shiurdiario.repositories.DapimRepo;

import java.util.List;

import javax.inject.Inject;

public class DapimViewModel extends SuperViewModel<DapimRepo>{

    private LiveData<WebResponse_previousDaf> mPreviousDapim;
    private LiveData<WebResponse_shiurDaf> mShiurDapim;

    @Inject
    public DapimViewModel(DapimRepo repository) {
        super(repository);
    }

    public void initPreviousDapim(String currentDate) {
        mPreviousDapim = super.repository.getPreviousDapimData(currentDate);
    }

    public void initShiurDapim(String masechet) {
        super.repository.initData();
        mShiurDapim = super.repository.getShiurDapim(masechet, 0);
    }

    public void loadNewData(String masechet, int page) {
        mShiurDapim = super.repository.getShiurDapim(masechet, page);
    }

    public LiveData<WebResponse_previousDaf> getPreviousDapim() {
        return mPreviousDapim;
    }

    public LiveData<WebResponse_shiurDaf> getShiurDapim() {
        return mShiurDapim;
    }
}
