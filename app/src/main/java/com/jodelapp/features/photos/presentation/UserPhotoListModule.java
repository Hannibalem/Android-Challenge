package com.jodelapp.features.photos.presentation;


import com.jodelapp.data.managers.CurrentUserManager;
import com.jodelapp.features.photos.usecases.GetPhotoListUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UserPhotoListModule {

    @Provides
    PhotoListViewModel provideViewModel(final CurrentUserManager currentUserManager,
                                        final GetPhotoListUseCase useCase) {
        return new PhotoListViewModel(currentUserManager, useCase);
    }
}
