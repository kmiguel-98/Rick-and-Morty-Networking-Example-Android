package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rick_and_morty_android_networking_example_app.R
import com.example.rick_and_morty_android_networking_example_app.databinding.CharacterListContainerFragmentBinding
import com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.character_list_recyclerview.CharacterListRecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListContainerFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels()

    private var _binding: CharacterListContainerFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CharacterListContainerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpCharacterListRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        configureToolbar()
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureToolbar() {

        binding.fragmentToolbar.title = "Rick & Morty Characters"

        //status bar color
        val window: Window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.R_n_M_palette_green)
    }

    private fun setUpCharacterListRecyclerView() {

        val recyclerViewFragment = CharacterListRecyclerViewFragment.newInstance(viewModel = viewModel) { characterId ->

            findNavController().navigate(
                CharacterListContainerFragmentDirections.actionCharacterListScreenFragmentToCharacterDetailFragment(characterId)
            )
        }

        childFragmentManager
            .beginTransaction()
            .replace(binding.characterListRecyclerviewContainer.id, recyclerViewFragment)
            .commit()
    }
}