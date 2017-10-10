package com.jodelapp.features.users.presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jodelapp.DaggerDependencies
import com.jodelapp.R
import com.jodelapp.databinding.FragmentUsersBinding
import javax.inject.Inject

class UserUserListFragment: Fragment() {

    @Inject
    lateinit var viewModel: UserListViewModel

    companion object {
        fun getInstance() = UserUserListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDependencies.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentUsersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchUsers()
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}