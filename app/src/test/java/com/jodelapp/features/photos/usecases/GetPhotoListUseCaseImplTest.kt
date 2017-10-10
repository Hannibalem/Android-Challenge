package com.jodelapp.features.photos.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.data.models.Album
import com.jodelapp.data.models.Photo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class GetPhotoListUseCaseImplTest {

    val api = mock<ApiService>()
    val tested: GetPhotoListUseCase = GetPhotoListUseCaseImpl(api)

    @Test
    fun `check photos from different albums are retrieved and put together`() {
        //Given
        val userId = "userId"

        val listAlbums = mutableListOf<Album>()
        val album1 = Album(userId, "1", "")
        val album2 = Album(userId, "2", "")
        listAlbums.add(album1)
        listAlbums.add(album2)
        whenever(api.getAlbums(userId)).thenReturn(Observable.just(listAlbums))

        val listPhotos1 = mutableListOf<Photo>()
        val photo1 = Photo(album1.id, "1", "", "", "")
        listPhotos1.add(photo1)
        whenever(api.getPhotos(album1.id)).thenReturn(Observable.just(listPhotos1))

        val listPhotos2 = mutableListOf<Photo>()
        val photo2 = Photo(album2.id, "2", "", "", "")
        listPhotos2.add(photo2)
        whenever(api.getPhotos(album2.id)).thenReturn(Observable.just(listPhotos2))

        val subscriber = TestObserver<List<Photo>>()

        //Then
        tested.getPhotos(userId).subscribe(subscriber)

        //When
        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
        subscriber.assertValue { it.size == 2 }
        subscriber.assertValue { it[0].equals(photo1) && it[1].equals(photo2) }
    }
}