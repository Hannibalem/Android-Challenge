package com.jodelapp.features.photos.presentation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jodelapp.DaggerDependencies;
import com.jodelapp.R;
import com.jodelapp.databinding.FragmentPhotosBinding;

import javax.inject.Inject;

public class UserPhotoListFragment extends Fragment {

    @Inject
    PhotoListViewModel viewModel;

    public static UserPhotoListFragment getInstance() {
        return new UserPhotoListFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDependencies.Companion.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        final FragmentPhotosBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.fetchPhotos();
    }

    @Override
    public void onDestroy() {
        viewModel.clear();
        super.onDestroy();
    }
}
