package com.jodelapp.features.photos.usecases;


import com.jodelapp.data.api.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class UserPhotoListUseCaseModule {

    @Provides
    GetPhotoListUseCase provideUseCase(ApiService api) {
        return new GetPhotoListUseCaseImpl(api);
    }
}
