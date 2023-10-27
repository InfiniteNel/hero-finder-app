package com.jroslar.heroapp.ui.detailhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jroslar.heroapp.databinding.FragmentDetailHeroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHeroFragment : Fragment() {

    private var _binding:FragmentDetailHeroBinding? = null
    private val binding get() = _binding!!


    private val args : DetailHeroFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initData()
    }

    private fun initData() {
        val detailHero = args.hero

        Glide
            .with(this)
            .load(detailHero.image.url)
            .into(binding.ivHeroAvatar)

        binding.tvHeroName.text = detailHero.name

        binding.tvFullNameData.text = detailHero.biography.full_name
        binding.tvAlterEgosData.text = detailHero.biography.alter_egos
        binding.tvAliasesData.text = detailHero.biography.aliases.toString()
        binding.tvPlaceBirthData.text = detailHero.biography.place_of_birth
        binding.tvFirstAppearanceData.text = detailHero.biography.first_appearance
        binding.tvPublisherData.text = detailHero.biography.publisher
        binding.tvAlignmentData.text = detailHero.biography.alignment

        binding.tvGenderData.text = detailHero.appearance.gender
        binding.tvRaceData.text = detailHero.appearance.race

        binding.tvOccupationData.text = detailHero.work.occupation
        binding.tvBaseData.text = detailHero.work.base
    }
}