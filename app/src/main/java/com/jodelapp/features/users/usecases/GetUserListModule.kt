package com.jodelapp.features.users.usecases

import com.jodelapp.data.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class GetUserListModule {

    @Provides
    fun provideUserCase(api: ApiService): GetUserListUseCase = GetUserListUseCaseImpl(api)
}