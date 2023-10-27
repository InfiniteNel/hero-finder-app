package com.jroslar.heroapp.ui.searchhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jroslar.heroapp.databinding.FragmentSearchHeroBinding
import com.jroslar.heroapp.ui.searchhero.adapter.SearchHeroAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchHeroFragment : Fragment() {

    private var _binding:FragmentSearchHeroBinding? = null
    private val binding get() = _binding!!

    private val searchHeroViewModel:SearchHeroViewModel by viewModels()

    private lateinit var searchHeroadapter: SearchHeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initUIState()
        initAdapter()
    }

    private fun initAdapter() {
        searchHeroadapter = SearchHeroAdapter(onItemSelect = {
            findNavController().navigate(
                SearchHeroFragmentDirections.actionSearchHeroFragmentToDetailHeroFragment(it)
            )
        })

        binding.rvSearchHeroList.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = searchHeroadapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchHeroViewModel.heroList.collect {
                    searchHeroadapter.updateList(it)
                }
            }
        }
    }
}