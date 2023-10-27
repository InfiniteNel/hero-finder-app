package com.jroslar.heroapp.ui.logshero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jroslar.heroapp.databinding.FragmentLogsHeroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogsHeroFragment : Fragment() {

    private var _binding:FragmentLogsHeroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogsHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}