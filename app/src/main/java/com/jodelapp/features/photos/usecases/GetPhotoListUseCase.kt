package com.jodelapp.features.photos.usecases

import com.jodelapp.data.models.Photo
import io.reactivex.Single

interface GetPhotoListUseCase {

    fun getPhotos(userId: String): Single<List<Photo>>
}