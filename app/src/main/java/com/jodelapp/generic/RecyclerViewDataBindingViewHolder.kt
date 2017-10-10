package com.jodelapp.generic

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

open class RecyclerViewDataBindingViewHolder<out B: ViewDataBinding>(val binding: B):
        RecyclerView.ViewHolder(binding.root)