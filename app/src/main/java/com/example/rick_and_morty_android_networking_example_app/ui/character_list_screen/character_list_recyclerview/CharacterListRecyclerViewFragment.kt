package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.character_list_recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rick_and_morty_android_networking_example_app.R
import com.example.rick_and_morty_android_networking_example_app.databinding.CharacterListRecyclerViewFragmentBinding


class CharacterListRecyclerViewFragment : Fragment() {

    private var _binding: CharacterListRecyclerViewFragmentBinding? = null
    private val binding get() = _binding!!


    //Lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = CharacterListRecyclerViewFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Private_Methods
    private fun setUpRecyclerView() {

    }
}