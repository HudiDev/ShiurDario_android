package com.hudiilfeld.shiurdiario.view_models;

import android.arch.lifecycle.LiveData;

import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.repositories.DedicationRepo;

public class DedicationViewModel extends SuperViewModel<DedicationRepo>{

    LiveData<WebResponse_masechet> mLiveData;

    public DedicationViewModel(DedicationRepo repository) {
        super(repository);
    }

    public void init(String currentDate) {
        mLiveData = super.repository.getData(currentDate);
    }

    public LiveData<WebResponse_masechet> getLiveData() {
        return mLiveData;
    }
}
