package com.hudiilfeld.shiurdiario.dagger_injections;


import com.hudiilfeld.shiurdiario.models.WebService;
import com.hudiilfeld.shiurdiario.repositories.DapimRepo;
import com.hudiilfeld.shiurdiario.repositories.MasechtotRepo;
import com.hudiilfeld.shiurdiario.repositories.SuperRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RepoModule {

    @Provides
    @Singleton
    Retrofit.Builder provideBuilder() {
        return new Builder().baseUrl("http://ws.shiurdiario.com")
                .addConverterFactory(GsonConverterFactory.create());
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    WebService provideWebService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }

    @Provides
    @Singleton
    DapimRepo provideDapimRepo(WebService webService) {
        return new DapimRepo(webService);
    }

    @Provides
    @Singleton
    MasechtotRepo providesDapimRepo(WebService webService) {
        return new MasechtotRepo(webService);
    }
    
}
