package com.hudiilfeld.shiurdiario;

import android.app.Application;

import com.hudiilfeld.shiurdiario.dagger_injections.AppComponent;
import com.hudiilfeld.shiurdiario.dagger_injections.DaggerAppComponent;
import com.hudiilfeld.shiurdiario.dagger_injections.RepoModule;

public class App extends Application{

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().repoModule(new RepoModule()).build();

    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
