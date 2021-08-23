package dev.mmoreno.brbad.xumak.characterslist.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.mmoreno.brbad.xumak.databinding.FragmentCharacterImageBinding


class CharacterImageFragment : DialogFragment() {

  val args: CharacterImageFragmentArgs by navArgs()
  private var _binding: FragmentCharacterImageBinding? = null
  private val binding get() = _binding!!

  override fun onStart() {
    super.onStart()
    val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
    val height = (resources.displayMetrics.heightPixels * 0.90).toInt()
    dialog?.window?.setLayout(width, height)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentCharacterImageBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.urlImage = args.urlImage
    setListeners()
  }

  private fun setListeners() {
    binding.closeButton.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}