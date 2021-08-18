package dev.mmoreno.brbad.xumak.fakedata

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingUtils {
  companion object {
    @JvmStatic
    @BindingAdapter("characterImage")
    fun loadImage(view: ImageView, characterImage: String) {
      Glide.with(view.context)
        .load(characterImage)
        .into(view)
    }
  }
}