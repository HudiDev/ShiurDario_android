package com.hudiilfeld.shiurdiario.dagger_injections;


import com.hudiilfeld.shiurdiario.views.daf_hayomi.Dedication_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Masechtot_fragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepoModule.class})
public interface AppComponent {


    void inject(Dapim_fragment dapimTab);

    void inject(Masechtot_fragment masechtotTab);

    void inject(Dedication_fragment dedicationTab);
}
