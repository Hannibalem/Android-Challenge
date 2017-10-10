package com.jodelapp.features.photos.presentation.recyclerview

import android.content.Context
import com.jodelapp.R
import com.jodelapp.databinding.PhotoLayoutBinding
import com.jodelapp.features.photos.presentation.PhotoViewModel
import com.jodelapp.generic.RecyclerViewDataBindingAdapter

class PhotosAdapter(context: Context, models: List<PhotoViewModel>):
        RecyclerViewDataBindingAdapter<PhotoViewModel, List<PhotoViewModel>, PhotoLayoutBinding, PhotoViewHolder>(context, models) {

    override fun getLayout() = R.layout.photo_layout

    override fun getViewHolder(binding: PhotoLayoutBinding) = PhotoViewHolder(binding)

    override fun bindItem(itemBinding: PhotoLayoutBinding, model: PhotoViewModel) {
        itemBinding.viewModel = model
    }
}