package dev.mmoreno.brbad.xumak.fakedata

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.mmoreno.brbad.xumak.util.options


@BindingAdapter("characterImage")
fun loadImage(view: ImageView, characterImage: String) {
  Glide.with(view.context)
    .load(characterImage)
    .apply(options)
    .dontAnimate()
    .let { request ->
      if (view.drawable != null) {
        request.placeholder(view.drawable.constantState?.newDrawable()?.mutate())
      } else {
        request
      }
    }
    .into(view)

}
