package dev.mmoreno.brbad.xumak.fakedata

import androidx.recyclerview.widget.DiffUtil
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity

class BreakingBadCharacterDiffUtil : DiffUtil.ItemCallback<BreakingBadCharacterEntity>() {
  override fun areItemsTheSame(
    oldItem: BreakingBadCharacterEntity,
    newItem: BreakingBadCharacterEntity
  ) = oldItem.id == newItem.id

  override fun areContentsTheSame(
    oldItem: BreakingBadCharacterEntity,
    newItem: BreakingBadCharacterEntity
  ) = oldItem.name == newItem.name
      && oldItem.isFavorite == newItem.isFavorite
      && oldItem.image == newItem.image
}