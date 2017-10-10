package com.jodelapp.features.photos.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.data.models.Photo
import io.reactivex.Single
import javax.inject.Inject

class GetPhotoListUseCaseImpl @Inject constructor(val api: ApiService): GetPhotoListUseCase {

    override fun getPhotos(userId: String): Single<List<Photo>> {
        return api.getAlbums(userId)
                .concatMapIterable { it }
                .concatMap { api.getPhotos(it.id) }
                .toList()
                .map { mutableListOf<Photo>().apply { for (list in it) addAll(list) } }
    }
}