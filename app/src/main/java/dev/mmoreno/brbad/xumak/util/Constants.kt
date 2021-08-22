package dev.mmoreno.brbad.xumak.util

import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.mmoreno.brbad.xumak.R


const val DATABASE_NAME = "breaking_bad_db.db"
const val URL_SERVICE = "https://www.breakingbadapi.com/api/"
const val URL_BREAKING_BAD = "URL_BREAKING_BAD"
const val READ_TIME_OUT = 30L
const val NETWORK_PAGE_SIZE = 10
const val INITIAL_LOAD_SIZE = 1

// Options for loading images with Glide
var options: RequestOptions = RequestOptions()
  .centerCrop()
  .placeholder(R.drawable.anim_loading_image)
  .error(R.drawable.ic_broken_image)
  .diskCacheStrategy(DiskCacheStrategy.ALL)
  .priority(Priority.HIGH)
  .dontAnimate()
  .dontTransform()
