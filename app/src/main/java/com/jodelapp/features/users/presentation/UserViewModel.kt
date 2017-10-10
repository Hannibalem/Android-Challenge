package com.jodelapp.features.users.presentation

import com.jodelapp.data.managers.CurrentUserManager
import com.jodelapp.data.models.User

class UserViewModel(val user: User, val currentUserManager: CurrentUserManager) {

    fun onClick() {
        currentUserManager.subject.onNext(user)
    }
}