package com.example.rick_and_morty_android_networking_example_app.ui.character_detail_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rick_and_morty_android_networking_example_app.R
import com.example.rick_and_morty_android_networking_example_app.databinding.CharacterDetailFragmentBinding


class CharacterDetailFragment : Fragment() {


    private var _binding: CharacterDetailFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}