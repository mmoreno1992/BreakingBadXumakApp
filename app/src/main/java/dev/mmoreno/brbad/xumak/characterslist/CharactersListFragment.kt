package dev.mmoreno.brbad.xumak.characterslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import dev.mmoreno.brbad.xumak.R
import dev.mmoreno.brbad.xumak.SharedViewModel
import dev.mmoreno.brbad.xumak.databinding.FragmentCharactersListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


//Annotation necessary in order to use the Paging library 3.0
@ExperimentalPagingApi
class CharactersListFragment : Fragment(),
  BreakingBadCharactersPagingAdapter.BreakingBadCharacterInteractionListener {

  //Injecting SharedViewModel using Koin
  private val viewModel: SharedViewModel by sharedViewModel()

  private var _binding: FragmentCharactersListBinding? = null

  //Syntax used in order to avoid the use of the safe operator (?.)
  private val binding get() = _binding!!

  //Special type of Adapter that works integrated with the Paging components
  val rvCharactersAdapter: BreakingBadCharactersPagingAdapter by lazy {
    BreakingBadCharactersPagingAdapter(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    setupObservers()
  }

  //Observing data
  private fun setupObservers() {
    viewModel.getCharactersList().observe(viewLifecycleOwner, {
      rvCharactersAdapter.submitData(lifecycle, it)
    })
  }

  //Set up the recycler view
  private fun setupRv() {
    binding.rvCharacters.apply {
      adapter = rvCharactersAdapter.withLoadStateFooter(
        CharactersListLoadStateFooterAdapter {
          rvCharactersAdapter.retry()
        }
      )
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }
    initAdapter()

    val animator = binding.rvCharacters.itemAnimator
    if (animator is SimpleItemAnimator) {
      animator.supportsChangeAnimations = false
    }
  }

  private fun initAdapter() {
    rvCharactersAdapter.addLoadStateListener { loadState ->
      if (loadState.source.refresh is LoadState.Loading) {
        binding.progressBarCharactersList.isVisible = true
        binding.emptyView.isVisible = false
      } else {
        binding.progressBarCharactersList.isVisible = false
      }
      /**
       * load state for errors and show the error message on UI
       * loadState.refresh - represents the load state for loading the PagingData for the first time.
       * loadState.prepend - represents the load state for loading data at the start of the list.
       * loadState.append - represents the load state for loading data at the end of the list.
       * */

      val errorState = loadState.source.refresh as? LoadState.Error // PagingSource
        ?: loadState.source.prepend as? LoadState.Error // PagingSource
        ?: loadState.source.append as? LoadState.Error // PagingSource
        ?: loadState.refresh as? LoadState.Error // RemoteMediator
        ?: loadState.append as? LoadState.Error // RemoteMediator
        ?: loadState.prepend as? LoadState.Error // RemoteMediator

      if (loadState.source.refresh is LoadState.NotLoading
        && loadState.append is LoadState.NotLoading
      ) {
        binding.emptyView.isVisible = rvCharactersAdapter.itemCount == 0
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onClickFavoriteButton(characterId: Int, isFavorite: Boolean) {
    viewModel.setBreakingBadCharacterAsFavorite(characterId, isFavorite)
    if (isFavorite) {
      Toast.makeText(activity, getString(R.string.marked_as_favorite), Toast.LENGTH_SHORT).show()
    }
  }

  override fun onClickCharacterImage(urlImage: String) {
    val direction =
      CharactersListFragmentDirections
        .actionCharactersListFragmentToCharacterImageFragment(
          urlImage
        )
    findNavController().navigate(
      direction
    )
  }

}