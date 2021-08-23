package dev.mmoreno.brbad.xumak.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mmoreno.brbad.xumak.R
import dev.mmoreno.brbad.xumak.databinding.ListItemLoadStateFooterBinding

class CharactersListLoadStateFooterAdapter(
  private val retryAction: () -> Unit
) : LoadStateAdapter<CharactersListLoadStateFooterAdapter.LoadStateViewHolder>() {

  override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
    holder.onBind(loadState)
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
    return LoadStateViewHolder.create(parent, retryAction)
  }

  /**
   * ViewHolder to show the state
   */
  class LoadStateViewHolder(
    private val binding: ListItemLoadStateFooterBinding,
    private val retryAction: () -> Unit
  ) : RecyclerView.ViewHolder(binding.root) {

    init {
      binding.retryButton.setOnClickListener {
        retryAction.invoke()
      }
    }

    fun onBind(loadState: LoadState) {
      when (loadState) {
        is LoadState.Loading -> {
          binding.retryButton.isVisible = false
          binding.errorMessage.isVisible = false
          binding.progressBar.isVisible = true
        }
        is LoadState.NotLoading -> {
          binding.retryButton.isVisible = false
          binding.errorMessage.isVisible = false
          binding.progressBar.isVisible = false
        }
        is LoadState.Error -> {
          binding.errorMessage.text = loadState.error.message
          binding.retryButton.isVisible = true
          binding.errorMessage.isVisible = true
          binding.progressBar.isVisible = false
        }
      }
    }

    companion object {

      fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.list_item_load_state_footer, parent, false)
        val binding = ListItemLoadStateFooterBinding.bind(view)
        return LoadStateViewHolder(binding, retry)
      }
    }
  }
}

