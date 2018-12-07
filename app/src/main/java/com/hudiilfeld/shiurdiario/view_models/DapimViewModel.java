package com.hudiilfeld.shiurdiario.view_models;

import android.arch.lifecycle.LiveData;
import com.hudiilfeld.shiurdiario.models.WebResponse_daf;
import com.hudiilfeld.shiurdiario.repositories.DapimRepo;

import javax.inject.Inject;

public class DapimViewModel extends SuperViewModel<DapimRepo>{

    private LiveData<WebResponse_daf> mDapim;

    @Inject
    public DapimViewModel(DapimRepo repository) {
        super(repository);
    }

    public void init(String currentDate) {
        mDapim = super.repository.getData(currentDate);
    }

    public LiveData<WebResponse_daf> getData() {
        return mDapim;
    }
}
