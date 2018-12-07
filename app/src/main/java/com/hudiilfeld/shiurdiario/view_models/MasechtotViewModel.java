package com.hudiilfeld.shiurdiario.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.repositories.MasechtotRepo;

public class MasechtotViewModel extends SuperViewModel<MasechtotRepo>{

    private LiveData<WebResponse_masechet> mLiveData;

    public MasechtotViewModel(MasechtotRepo repository) {
        super(repository);
    }

    public void init(String currentDate) {
        mLiveData = super.repository.getData(currentDate);
    }

    public LiveData<WebResponse_masechet> getliveData() {
        return mLiveData;
    }
}
