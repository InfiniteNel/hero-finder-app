package com.jroslar.heroapp.ui.searchhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jroslar.heroapp.databinding.FragmentSearchHeroBinding

class SearchHeroFragment : Fragment() {

    private var _binding:FragmentSearchHeroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}