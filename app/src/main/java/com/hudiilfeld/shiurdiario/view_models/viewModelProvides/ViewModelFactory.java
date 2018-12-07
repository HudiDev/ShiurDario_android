package com.hudiilfeld.shiurdiario.view_models.viewModelProvides;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.hudiilfeld.shiurdiario.repositories.DapimRepo;
import com.hudiilfeld.shiurdiario.repositories.DedicationRepo;
import com.hudiilfeld.shiurdiario.repositories.MasechtotRepo;
import com.hudiilfeld.shiurdiario.repositories.SuperRepo;
import com.hudiilfeld.shiurdiario.view_models.DapimViewModel;
import com.hudiilfeld.shiurdiario.view_models.DedicationViewModel;
import com.hudiilfeld.shiurdiario.view_models.MasechtotViewModel;

import javax.inject.Inject;

public class ViewModelFactory<T extends SuperRepo> implements ViewModelProvider.Factory{

    T repository;

    @Inject
    public ViewModelFactory(T repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <VM extends ViewModel> VM create(@NonNull Class<VM> modelClass) {
        if (modelClass.isAssignableFrom(DapimViewModel.class)) {
            if (repository instanceof DapimRepo) {
                return (VM) new DapimViewModel((DapimRepo) repository);
            }
            throw new ClassCastException("repository ain't instance of DapimRepo");
        }
        if (modelClass.isAssignableFrom(MasechtotViewModel.class)) {
            if (repository instanceof MasechtotRepo) {
                return (VM) new MasechtotViewModel((MasechtotRepo) repository);
            }
            throw new ClassCastException("repository ain't instance of MasechtotRepo");
        }
        if (modelClass.isAssignableFrom(DedicationViewModel.class)) {
            if (repository instanceof DedicationRepo) {
                return (VM) new DedicationViewModel((DedicationRepo) repository);
            }
            throw new ClassCastException("repository ain't instance of DedicationRepo");

        }
        return null;
    }
}
