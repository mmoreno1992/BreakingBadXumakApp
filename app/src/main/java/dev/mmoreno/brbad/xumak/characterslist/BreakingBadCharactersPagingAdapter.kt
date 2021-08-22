package dev.mmoreno.brbad.xumak.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import dev.mmoreno.brbad.xumak.databinding.ListItemBreakingBadCharacterBinding
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity

/**
 * Paging adapter that gets chunks of data (pages)
 * Uses dataBinding
 */
class BreakingBadCharactersPagingAdapter(
  val onIsFavoriteButtonClick: (characterId: Int, isFavorite: Boolean) -> Unit
) :
  PagingDataAdapter<BreakingBadCharacterEntity, BreakingBadCharactersPagingAdapter.ViewHolder>(
    BreakingBadCharacterDiffUtil()
  ) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ListItemBreakingBadCharacterBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding)
  }

  inner class ViewHolder(val binding: ListItemBreakingBadCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.likeButton.setOnLikeListener(
        object : OnLikeListener {
          override fun liked(likeButton: LikeButton?) {
            getItem(bindingAdapterPosition)?.let { character ->
              onIsFavoriteButtonClick(character.id, true)
            }
          }

          override fun unLiked(likeButton: LikeButton?) {
            getItem(bindingAdapterPosition)?.let { character ->
              onIsFavoriteButtonClick(character.id, false)
            }
          }
        }
      )
    }

    fun bind(item: BreakingBadCharacterEntity?) {
      item?.let {
        binding.character = item
        binding.executePendingBindings()
      }
    }
  }

  fun interface BreakingBadCharacterInteractionListener {
    fun onClickFavoriteButton(isFavorite: Boolean)
  }
}

