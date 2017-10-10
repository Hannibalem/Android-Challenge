package com.jodelapp.features.photos.presentation.bindingadapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

private const val BIND_IMAGE = "image"

@BindingAdapter(BIND_IMAGE)
fun ImageView._bindImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context)
              .load(it)
              .centerCrop()
              .into(this)
    }
}