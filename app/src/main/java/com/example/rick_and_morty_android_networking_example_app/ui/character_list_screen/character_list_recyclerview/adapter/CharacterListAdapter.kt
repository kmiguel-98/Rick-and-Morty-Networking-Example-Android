package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.character_list_recyclerview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

class CharacterListAdapter(private val characterList: List<Character>): RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    inner class CharacterListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val binding = ChipItemCellBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {

    }

    override fun getItemCount() = characterList.count()

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {

    }
}