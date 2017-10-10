package com.jodelapp.data.managers

import com.jodelapp.data.models.User
import io.reactivex.subjects.BehaviorSubject

class CurrentUserManagerImpl: CurrentUserManager {

    override var subject: BehaviorSubject<User> = BehaviorSubject.create<User>()

    init {
        subject.onNext(User.NONE)
    }
}