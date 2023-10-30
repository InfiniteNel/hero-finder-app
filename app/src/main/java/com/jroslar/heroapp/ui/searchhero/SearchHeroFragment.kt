package com.jroslar.heroapp.ui.searchhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jroslar.heroapp.R
import com.jroslar.heroapp.core.extensions.hideKeyboard
import com.jroslar.heroapp.databinding.FragmentSearchHeroBinding
import com.jroslar.heroapp.ui.searchhero.adapter.SearchHeroAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchHeroFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

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
        initListener()
    }

    private fun initListener() {
        binding.svSearchHero.setOnQueryTextListener(this)
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
                searchHeroViewModel.state.collect {
                    binding.apply {
                        pbSearchHeroList.isVisible = false
                        ivState.isVisible = false
                        tvTitleResult.isVisible = false
                        rvSearchHeroList.isVisible = false
                    }

                    when(it) {
                        SearchHeroState.Loading -> loadingState()
                        is SearchHeroState.Error -> errorState()
                        is SearchHeroState.Success -> successState(it)
                        is SearchHeroState.NoData -> noDataState(it)
                    }
                }
            }
        }
    }

    private fun noDataState(state: SearchHeroState.NoData) {
        binding.ivState.isVisible = true
        binding.tvTitleResult.isVisible = true
        searchHeroadapter.updateList(state.heroList)
        binding.ivState.setImageResource(R.drawable.busqueda_result)
    }

    private fun successState(state: SearchHeroState.Success) {
        binding.tvTitleResult.isVisible = true
        binding.rvSearchHeroList.isVisible = true
        searchHeroadapter.updateList(state.heroList)
    }

    private fun errorState() {
        binding.ivState.isVisible = true
        binding.tvTitleResult.isVisible = true
        binding.tvTitleResult.text = getString(R.string.tvTitleResultTextError)
        binding.ivState.setImageResource(R.drawable.pagina_no_encontrada)
    }

    private fun loadingState() {
        binding.pbSearchHeroList.isVisible = true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchHeroViewModel.getListHeroByName(query.lowercase())
            binding.tvTitleResult.text = getString(R.string.tvTitleResultText).plus(query)
        }

        requireView().hideKeyboard()

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}