package com.jodelapp.data.managers

import com.jodelapp.data.models.User
import io.reactivex.subjects.BehaviorSubject

interface CurrentUserManager {
    var subject: BehaviorSubject<User>
}