package com.jodelapp.features.users.presentation

import com.jodelapp.data.managers.CurrentUserManager
import com.jodelapp.features.users.usecases.GetUserListUseCase
import dagger.Module
import dagger.Provides

@Module
class UserListModule {

    @Provides
    fun provideViewModel(currentUserManager: CurrentUserManager, useCase: GetUserListUseCase) =
            UserListViewModel(currentUserManager, useCase)
}