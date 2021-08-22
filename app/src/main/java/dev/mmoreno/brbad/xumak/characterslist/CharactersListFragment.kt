package dev.mmoreno.brbad.xumak.characterslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import dev.mmoreno.brbad.xumak.R
import dev.mmoreno.brbad.xumak.SharedViewModel
import dev.mmoreno.brbad.xumak.databinding.FragmentCharactersListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


//Annotation necessary in order to use the Paging library 3.0
@ExperimentalPagingApi
class CharactersListFragment : Fragment() {

  //Injecting my viewmodel using Koin
  private val viewModel: SharedViewModel by sharedViewModel()

  private var _binding: FragmentCharactersListBinding? = null

  //Syntax used in order to avoid the use of the safe operator (?.)
  private val binding get() = _binding!!

  //Special type of Adapter that works integrated with the Paging components
  val rvCharactersAdapter: BreakingBadCharactersPagingAdapter by lazy {
    BreakingBadCharactersPagingAdapter { characterId, isFavorite ->
      viewModel.setBreakingBadCharacterAsFavorite(characterId, isFavorite)
      if (isFavorite) {
        Toast.makeText(activity, getString(R.string.marked_as_favorite), Toast.LENGTH_SHORT).show()
      }
    }
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

  private fun setupObservers() {
    viewModel.getCharactersList().observe(viewLifecycleOwner, {
      rvCharactersAdapter.submitData(lifecycle, it)
    })
  }

  private fun setupRv() {
    binding.rvCharacters.apply {
      adapter = rvCharactersAdapter
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }
    val animator = binding.rvCharacters.itemAnimator
    if (animator is SimpleItemAnimator) {
      animator.supportsChangeAnimations = false
    }
  }

  /**
   * Get rid of the reference if you want to avoid
   * memory leaks
   */
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}