package com.jodelapp

import android.app.Application
import com.jodelapp.features.photos.presentation.*
import com.jodelapp.features.photos.usecases.UserPhotoListUseCaseModule
import com.jodelapp.features.todos.presentation.DaggerUserTodoListComponent
import com.jodelapp.features.todos.presentation.UserTodoListModule
import com.jodelapp.features.todos.presentation.UserTodoListView
import com.jodelapp.features.todos.usecases.UserTodoListUseCaseModule
import com.jodelapp.features.users.presentation.DaggerUserListComponent
import com.jodelapp.features.users.presentation.UserListModule
import com.jodelapp.features.users.presentation.UserUserListFragment
import com.jodelapp.features.users.usecases.GetUserListModule
import com.jodelapp.views.activities.DaggerMainActivityComponent
import com.jodelapp.views.activities.MainActivity
import com.jodelapp.views.activities.MainActivityModule

class DaggerDependencies {

    companion object {

        lateinit var injector: AppComponent
            private set

        fun init(application: Application) {
            injector = DaggerAppComponent.builder().appModule(AppModule(application)).build()
        }

        fun inject(activity: MainActivity) {
            DaggerMainActivityComponent.builder()
                    .appComponent(injector)
                    .mainActivityModule(MainActivityModule(activity))
                    .build()
                    .inject(activity)
        }

        fun inject(fragment: UserTodoListView) {
            DaggerUserTodoListComponent.builder()
                    .appComponent(injector)
                    .userTodoListModule(UserTodoListModule(fragment))
                    .userTodoListUseCaseModule(UserTodoListUseCaseModule())
                    .build()
                    .inject(fragment)
        }

        fun inject(fragment: UserPhotoListFragment) {
            DaggerUserPhotoListComponent.builder()
                    .appComponent(injector)
                    .userPhotoListModule(UserPhotoListModule())
                    .userPhotoListUseCaseModule(UserPhotoListUseCaseModule())
                    .build()
                    .inject(fragment)
        }

        fun inject(fragment: UserUserListFragment) {
            DaggerUserListComponent.builder()
                    .appComponent(injector)
                    .userListModule(UserListModule())
                    .getUserListModule(GetUserListModule())
                    .build()
                    .inject(fragment)
        }
    }
}