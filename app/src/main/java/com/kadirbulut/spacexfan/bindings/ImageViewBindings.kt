package com.kadirbulut.spacexfan.bindings

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(
    view: View,
    imageUrl: String?
) {
    val image: AppCompatImageView = view as AppCompatImageView
    Glide.with(image.context)
        .load(imageUrl)
        .into(image)
}
