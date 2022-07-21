package com.samaritan.assignment.bindingAdapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("pokemonImage")
    fun loadPoster(view: AppCompatImageView, imageUrl: String?) {
        val url = imageUrl
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}