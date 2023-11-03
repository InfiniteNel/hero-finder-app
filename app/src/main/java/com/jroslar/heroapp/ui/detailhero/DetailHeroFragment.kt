package com.jroslar.heroapp.ui.detailhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jroslar.heroapp.R
import com.jroslar.heroapp.databinding.FragmentDetailHeroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHeroFragment : Fragment(), MenuProvider {

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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.close_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.close -> {
                findNavController().navigateUp()
                true
            }
            else -> true
        }
    }

    private fun initUI() {
        initData()
        initMenu()
    }

    private fun initMenu() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    //Inicializa los datos del modelo de datos en la vista
    private fun initData() {
        val detailHero = args.hero

        Glide
            .with(this)
            .load(detailHero.image.url)
            .into(binding.ivHeroAvatar)

        binding.apply {
            tvHeroName.text = detailHero.name

            tvFullNameData.text = detailHero.biography.full_name
            tvAlterEgosData.text = detailHero.biography.alter_egos
            tvAliasesData.text = detailHero.biography.aliases.toString()
            tvPlaceBirthData.text = detailHero.biography.place_of_birth
            tvFirstAppearanceData.text = detailHero.biography.first_appearance
            tvPublisherData.text = detailHero.biography.publisher
            tvAlignmentData.text = detailHero.biography.alignment

            tvintelligenceData.text = detailHero.powerstats.intelligence
            tvstrengthData.text = detailHero.powerstats.strength
            tvspeedData.text = detailHero.powerstats.speed
            tvdurabilityData.text = detailHero.powerstats.durability
            tvpowerData.text = detailHero.powerstats.power
            tvcombatData.text = detailHero.powerstats.combat

            tvGenderData.text = detailHero.appearance.gender
            tvRaceData.text = detailHero.appearance.race

            tvOccupationData.text = detailHero.work.occupation
            tvBaseData.text = detailHero.work.base
        }
    }
}