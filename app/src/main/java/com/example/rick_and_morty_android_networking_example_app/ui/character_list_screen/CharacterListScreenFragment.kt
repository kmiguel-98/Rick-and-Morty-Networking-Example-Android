package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rick_and_morty_android_networking_example_app.R

class CharacterListScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.character_list_screen_fragment, container, false)
    }
}