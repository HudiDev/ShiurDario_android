package com.hudiilfeld.shiurdiario.view_models;

import android.arch.lifecycle.LiveData;
import com.hudiilfeld.shiurdiario.models.WebResponse_previousDaf;
import com.hudiilfeld.shiurdiario.models.WebResponse_shiurDaf;
import com.hudiilfeld.shiurdiario.repositories.DapimRepo;

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

    public void initShiurimDapim(String mashechet, int page) {
        mShiurDapim = super.repository.getShiurDapim(mashechet, page);
    }

    public LiveData<WebResponse_previousDaf> getPreviousDapim() {
        return mPreviousDapim;
    }

    public LiveData<WebResponse_shiurDaf> getShiurDapim() {
        return mShiurDapim;
    }
}
