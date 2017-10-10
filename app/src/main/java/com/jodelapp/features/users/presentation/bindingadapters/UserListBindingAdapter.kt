package com.jodelapp.features.users.presentation.bindingadapters

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jodelapp.features.users.presentation.UserViewModel
import com.jodelapp.features.users.presentation.recyclerview.UsersAdapter

private const val BIND_USERS = "users"

@BindingAdapter(BIND_USERS)
fun RecyclerView._bindUsers(models: List<UserViewModel>?) {
    models?.let {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context)
            adapter = UsersAdapter(context, it)
        } else {
            (adapter as UsersAdapter).models = it
            adapter.notifyDataSetChanged()
        }
    }
}