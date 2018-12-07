package com.hudiilfeld.shiurdiario.dagger_injections;


import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.AllMasechtot_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.PreviousDapim_tab;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepoModule.class})
public interface AppComponent {


    void inject(PreviousDapim_tab dapimTab);

    void inject(AllMasechtot_tab masechtotTab);
}
