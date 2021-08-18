package dev.mmoreno.brbad.xumak.fakedata

import androidx.recyclerview.widget.DiffUtil

class CharacterDiffUtil : DiffUtil.ItemCallback<BreakingBadCharacter>() {
  override fun areItemsTheSame(
    oldItem: BreakingBadCharacter,
    newItem: BreakingBadCharacter
  ) = oldItem.id == newItem.id

  override fun areContentsTheSame(
    oldItem: BreakingBadCharacter,
    newItem: BreakingBadCharacter
  ) = oldItem.name == newItem.name
}