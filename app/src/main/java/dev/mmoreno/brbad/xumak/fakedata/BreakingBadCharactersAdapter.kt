package dev.mmoreno.brbad.xumak.fakedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mmoreno.brbad.xumak.databinding.ListItemCharacterBinding

class BreakingBadCharactersAdapter :
  ListAdapter<BreakingBadCharacter, BreakingBadCharactersAdapter.ViewHolder>(CharacterDiffUtil()) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent)
  }

  class ViewHolder private constructor(val binding: ListItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BreakingBadCharacter) {
      binding.character = item
      binding.executePendingBindings()
    }

    companion object {
      fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCharacterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
      }
    }
  }
}
