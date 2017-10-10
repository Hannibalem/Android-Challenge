package com.jodelapp.features.users.presentation

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import com.jodelapp.BR
import com.jodelapp.data.managers.CurrentUserManager
import com.jodelapp.data.models.User
import com.jodelapp.features.users.usecases.GetUserListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserListViewModel(private val currentUserManager: CurrentUserManager,
                        private val useCase: GetUserListUseCase): BaseObservable() {

    private val disposables = CompositeDisposable()
    private var currentUser = User.NONE

    @Bindable
    var currentUserName: String = ""
        get() = if (currentUser.equals(User.NONE)) "No user logged in" else "Current user: ${currentUser.username}"

    init {
        disposables.add(currentUserManager.subject.subscribe {
            if (!it.id.isEmpty()) {
                currentUser = it
                notifyPropertyChanged(BR.currentUserName)
                notifyPropertyChanged(BR.users)
            }
        })
    }

    @Bindable
    var users = emptyList<UserViewModel>()
        get() = field.filter { !it.user.id.equals(currentUser.id) }

    @Bindable
    var loading = View.VISIBLE
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.loading)
            }
        }

    fun fetchUsers() {
        useCase.getUsers()
                .map { it.map { UserViewModel(it, currentUserManager) } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contentLoaded(it) }, { loading = View.GONE })
    }

    fun clear() {
        disposables.clear()
    }

    private fun contentLoaded(users: List<UserViewModel>) {
        loading = View.GONE
        this.users = users
        notifyPropertyChanged(BR.users)
    }
}