package com.hudiilfeld.shiurdiario.dagger_injections;


import com.hudiilfeld.shiurdiario.views.daf_hayomi.DedicationTab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.AllMasechtot_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_tab;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepoModule.class})
public interface AppComponent {


    void inject(Dapim_tab dapimTab);

    void inject(AllMasechtot_tab masechtotTab);

    void inject(DedicationTab dedicationTab);
}
