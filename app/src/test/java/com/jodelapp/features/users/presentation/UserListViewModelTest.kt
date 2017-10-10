package com.jodelapp.features.users.presentation

import android.view.View
import com.jodelapp.data.managers.CurrentUserManager
import com.jodelapp.data.models.User
import com.jodelapp.features.users.usecases.GetUserListUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.BeforeClass



class UserListViewModelTest {

    val useCase = mock<GetUserListUseCase>()
    val manager = mock<CurrentUserManager>()
    val subject = BehaviorSubject.create<User>()
    val currentUser = User("id", "name", "name", "")
    lateinit var tested: UserListViewModel

    companion object {

        @BeforeClass @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun setUp() {
        subject.onNext(currentUser)
        whenever(manager.subject).thenReturn(subject)
        tested = UserListViewModel(manager, useCase)
    }

    @Test
    fun `check current username gets updated when subject does`() {
        //Given
        val name = "Juan"
        val user = User("id", "name", name, "")

        //When
        subject.onNext(user)

        //Then
        assertEquals("Current user: $name", tested.currentUserName)
    }

    @Test
    fun `check users are fetched and current one is filtered out when retrieved`() {
        //Given
        val users = mutableListOf<User>()
        users.add(User.NONE)
        users.add(currentUser)

        whenever(useCase.getUsers()).thenReturn(Single.just(users))

        //When
        tested.fetchUsers()

        //Then
        assertEquals(1, tested.users.size)
        assertEquals(User.NONE, tested.users[0].user)
        assertEquals(View.GONE, tested.loading)
    }
}