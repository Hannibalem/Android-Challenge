package com.jodelapp.features.users.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.data.models.User
import io.reactivex.Single

class GetUserListUseCaseImpl(val api: ApiService): GetUserListUseCase {

    override fun getUsers(): Single<List<User>> = api.users.single(emptyList())
}