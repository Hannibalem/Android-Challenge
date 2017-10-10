package com.jodelapp.features.users.presentation.recyclerview

import android.content.Context
import com.jodelapp.R
import com.jodelapp.databinding.UserLayoutBinding
import com.jodelapp.features.users.presentation.UserViewModel
import com.jodelapp.generic.RecyclerViewDataBindingAdapter

class UsersAdapter(context: Context, models: List<UserViewModel>):
        RecyclerViewDataBindingAdapter<UserViewModel, List<UserViewModel>, UserLayoutBinding, UserViewHolder>
        (context, models) {

    override fun getLayout() = R.layout.user_layout

    override fun getViewHolder(binding: UserLayoutBinding) = UserViewHolder(binding)

    override fun bindItem(itemBinding: UserLayoutBinding, model: UserViewModel) {
        itemBinding.viewModel = model
    }

}