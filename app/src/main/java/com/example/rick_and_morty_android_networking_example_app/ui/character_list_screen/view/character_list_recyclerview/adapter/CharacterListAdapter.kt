package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.view.character_list_recyclerview.adapter

import android.annotation.SuppressLint
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rick_and_morty_android_networking_example_app.R
import com.example.rick_and_morty_android_networking_example_app.databinding.CharacterItemCellLayoutBinding
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

class CharacterListAdapter(
    private val characterList: MutableList<Character>,
    private val onClickAction: ((Int) -> Unit)
    ): RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    inner class CharacterListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CharacterItemCellLayoutBinding.bind(itemView)

        fun configure(character: Character) {
            binding.nameTextview.text = character.name
            binding.statusSpeciesTextview.text = view.context.getString(R.string.status_species_text, character.species, character.status)
            binding.lastKnownLocationTextview.text = character.location.name
            binding.originTextview.text = character.origin.name

            Glide.with(view)
                .load(character.image.toString())
                .placeholder(R.drawable.placeholder_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.characterImageview)

            binding.itemCellContainer.setOnClickListener {
                onClickAction.invoke(character.id)
            }
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCharacterList: List<Character>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }
}