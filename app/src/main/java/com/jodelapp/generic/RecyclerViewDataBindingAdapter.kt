package com.jodelapp.generic

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class RecyclerViewDataBindingAdapter<M, L: List<M>, B: ViewDataBinding,
        VH: RecyclerViewDataBindingViewHolder<B>>(val context: Context, var models: L): RecyclerView.Adapter<VH>() {

    final override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        return getViewHolder(DataBindingUtil.inflate<B>(LayoutInflater.from(context), getLayout(), parent, false))
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        bindItem(holder.binding, models[position])
        holder.binding.executePendingBindings()
    }

    final override fun getItemCount() = models.size

    abstract fun getLayout(): Int

    abstract fun getViewHolder(binding: B): VH

    abstract fun bindItem(itemBinding: B, model: M)
}