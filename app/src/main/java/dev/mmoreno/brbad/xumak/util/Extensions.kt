package dev.mmoreno.brbad.xumak.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity
import dev.mmoreno.brbad.xumak.networking.model.BreakingBadCharacterResponse

/**
 * Extension function for mapping a list of response entities to
 * database entities
 */
fun List<BreakingBadCharacterResponse>.toBreakingBadCharacterEntities(offset: Int) =
  this.mapIndexed { index, character ->
    BreakingBadCharacterEntity(
      id = character.id,
      name = character.name,
      nickname = character.nickname,
      image = character.img,
      isFavorite = false,
      customOffset = offset + index
    )
  }


fun DialogFragment.setWidthPercent(percentage: Int) {
  val percent = percentage.toFloat() / 100
  val dm = Resources.getSystem().displayMetrics
  val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
  val percentWidth = rect.width() * percent
  dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

