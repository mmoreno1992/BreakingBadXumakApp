package dev.mmoreno.brbad.xumak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.mmoreno.brbad.xumak.fakedata.BreakingBadCharactersAdapter
import dev.mmoreno.brbad.xumak.fakedata.SharedViewModel


class CharactersListFragment : Fragment() {

  val viewModel: SharedViewModel by activityViewModels()
  val rvCharactersAdapter: BreakingBadCharactersAdapter by lazy {
    BreakingBadCharactersAdapter()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_characters_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val rv = view.findViewById<RecyclerView>(R.id.rvCharacters)
    rv.apply {
      adapter = rvCharactersAdapter
      layoutManager = LinearLayoutManager(view.context)
      addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
    }
    viewModel.charactersLiveData.observe(viewLifecycleOwner, {
      rvCharactersAdapter.submitList(it)
    })
  }

}