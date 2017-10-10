package com.jodelapp.features.users.usecases

import com.jodelapp.data.models.User
import io.reactivex.Single

interface GetUserListUseCase {

    fun getUsers(): Single<List<User>>
}