package com.hudiilfeld.shiurdiario.view_models;

import android.arch.lifecycle.ViewModel;

public class SuperViewModel<T> extends ViewModel{

    T repository;

    public SuperViewModel(T repository) {
        this.repository = repository;
    }
}
