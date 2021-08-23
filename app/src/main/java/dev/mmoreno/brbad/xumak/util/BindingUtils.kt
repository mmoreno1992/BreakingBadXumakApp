package dev.mmoreno.brbad.xumak.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("characterImage")
fun loadImage(view: ImageView, characterImage: String) {
  Glide.with(view.context)
    .load(characterImage)
    .apply(options)
    .into(view)

}
