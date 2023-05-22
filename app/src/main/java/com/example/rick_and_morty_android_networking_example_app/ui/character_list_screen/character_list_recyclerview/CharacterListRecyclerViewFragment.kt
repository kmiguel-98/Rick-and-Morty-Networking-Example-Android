package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.character_list_recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_android_networking_example_app.databinding.CharacterListRecyclerViewFragmentBinding
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.CharacterListViewModel
import com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen.character_list_recyclerview.adapter.CharacterListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class CharacterListRecyclerViewFragment private constructor() : Fragment() {

    lateinit var viewModel: CharacterListViewModel
    private set

    private var _binding: CharacterListRecyclerViewFragmentBinding? = null
    private val binding get() = _binding!!

    private var characterListAdapter: CharacterListAdapter? = null

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

        setObservers()
        setUpRecyclerView(viewModel.state.value.characterList)
        super.onViewCreated(view, savedInstanceState)

        viewModel.recyclerViewDidLoad()
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

    // Private_Methods
    private fun setUpRecyclerView(characterList: List<Character>) {

        characterListAdapter = CharacterListAdapter(characterList.toMutableList())
        binding.characterListRecyclerview.adapter = characterListAdapter
    }

    private fun setObservers() {

        // viewModel State onChange Listener
        viewModel.state.onEach { state ->

            if (state.error.isBlank() && !state.isLoading) {
                characterListAdapter?.updateData(state.characterList)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        // OnScrollListener
        binding.characterListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    if (!viewModel.state.value.isLoading)
                        viewModel.recyclerDidScrollUntilBottom()
                }
            }
        })
    }

    companion object {

        fun newInstance(viewModel: CharacterListViewModel): CharacterListRecyclerViewFragment {
            val fragment = CharacterListRecyclerViewFragment()
            fragment.viewModel = viewModel
            return fragment
        }
    }
}