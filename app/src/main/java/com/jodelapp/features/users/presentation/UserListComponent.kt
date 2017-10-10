package com.jodelapp.features.users.presentation

import com.jodelapp.AppComponent
import com.jodelapp.di.scope.ViewScope
import com.jodelapp.features.users.usecases.GetUserListModule
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(UserListModule::class, GetUserListModule::class))
interface UserListComponent {

    fun inject(fragment: UserUserListFragment)
}