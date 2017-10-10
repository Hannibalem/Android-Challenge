package com.jodelapp.features.photos.presentation

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import com.jodelapp.BR
import com.jodelapp.data.managers.CurrentUserManager
import com.jodelapp.data.models.User
import com.jodelapp.features.photos.usecases.GetPhotoListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PhotoListViewModel(currentUserManager: CurrentUserManager,
                         private val useCase: GetPhotoListUseCase): BaseObservable() {

    private val disposables = CompositeDisposable()
    private var user = User.NONE

    init { disposables.add(currentUserManager.subject.subscribe { user = it }) }

    @Bindable
    var photos = emptyList<PhotoViewModel>()
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.photos)
            }
        }

    @Bindable
    var loading = View.VISIBLE
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.loading)
            }
        }

    fun fetchPhotos() {
        disposables.add(useCase.getPhotos(user.id)
                .map { it.map { PhotoViewModel(it) } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contentLoaded(it) }, { loading = View.GONE }))
    }

    fun clear() {
        disposables.clear()
    }

    private fun contentLoaded(photos: List<PhotoViewModel>) {
        loading = View.GONE
        this.photos = photos
    }
}