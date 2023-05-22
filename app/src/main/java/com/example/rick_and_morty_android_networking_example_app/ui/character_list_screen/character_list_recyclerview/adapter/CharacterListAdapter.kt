package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.character_list_recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_android_networking_example_app.R
import com.example.rick_and_morty_android_networking_example_app.databinding.CharacterItemCellLayoutBinding
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

class CharacterListAdapter(private val characterList: List<Character>): RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    inner class CharacterListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CharacterItemCellLayoutBinding.bind(itemView)

        fun configure(character: Character) {
            binding.nameTextview.text = character.name
            binding.statusSpeciesTextview.text = view.context.getString(R.string.status_species_text, character.species, character.status)
            binding.lastKnownLocationTextview.text = character.location.name
            binding.originTextview.text = character.origin.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item_cell_layout, parent, false)
        return CharacterListViewHolder(view)
    }

    override fun getItemCount() = characterList.count()

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {

        holder.configure(characterList[position])
    }
}