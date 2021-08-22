package dev.mmoreno.brbad.xumak.networking.model

import com.google.gson.annotations.SerializedName

class BreakingBadCharacterResponse(
  @SerializedName("char_id")
  val id: Int,
  val name: String,
  val nickname: String,
  val img: String
)

