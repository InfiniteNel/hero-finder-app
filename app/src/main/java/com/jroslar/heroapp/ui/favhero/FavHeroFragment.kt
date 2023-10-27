package com.jroslar.heroapp.ui.favhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jroslar.heroapp.databinding.FragmentFavHeroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavHeroFragment : Fragment() {

    private var _binding:FragmentFavHeroBinding? = null
    private val binding get() = _binding!!

    private val favHeroViewModel:FavHeroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}