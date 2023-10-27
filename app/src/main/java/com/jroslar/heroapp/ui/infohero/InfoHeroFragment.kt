package com.jroslar.heroapp.ui.infohero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jroslar.heroapp.databinding.FragmentInfoHeroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoHeroFragment : Fragment() {

    private var _binding:FragmentInfoHeroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}