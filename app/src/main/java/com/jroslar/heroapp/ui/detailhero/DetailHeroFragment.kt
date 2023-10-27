package com.jroslar.heroapp.ui.detailhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jroslar.heroapp.databinding.FragmentDetailHeroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHeroFragment : Fragment() {

    private var _binding:FragmentDetailHeroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}