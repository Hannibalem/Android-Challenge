package com.jodelapp.features.photos.presentation.bindingadapters

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jodelapp.features.photos.presentation.PhotoViewModel
import com.jodelapp.features.photos.presentation.recyclerview.PhotosAdapter

private const val BIND_PHOTOS = "photos"

@BindingAdapter(BIND_PHOTOS)
fun RecyclerView._bindPhotos(models: List<PhotoViewModel>?) {
    models?.let {
        if (it.isNotEmpty() && adapter == null) {
            layoutManager = LinearLayoutManager(context)
            adapter = PhotosAdapter(context, models)
        }
    }
}