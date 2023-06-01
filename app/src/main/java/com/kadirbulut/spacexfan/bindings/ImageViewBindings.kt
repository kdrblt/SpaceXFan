package com.kadirbulut.spacexfan.bindings

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kadirbulut.spacexfan.R

/*
 * Data binding adapter load image from url in xml
 */
@BindingAdapter("imageUrl")
fun loadImage(
    view: View,
    imageUrl: String?
) {
    val image: AppCompatImageView = view as AppCompatImageView
    val options = RequestOptions()
        .placeholder(placeholderProgressBar(image.context))
        .error(R.drawable.ic_exclamation)
    Glide.with(image.context)
        .setDefaultRequestOptions(options)
        .load(imageUrl)
        .into(image)
}

/*
 * For show progress drawable while image is loading
 */
fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        setColorSchemeColors(ContextCompat.getColor(context, R.color.orange))
        start()
    }
}
