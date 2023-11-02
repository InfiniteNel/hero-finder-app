package com.jroslar.heroapp.ui.randomhero

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jroslar.heroapp.R
import com.jroslar.heroapp.databinding.FragmentRandomHeroBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomHeroFragment : Fragment() {

    private var _binding:FragmentRandomHeroBinding? = null
    private val binding get() = _binding!!

    private val randomHeroViewModel:RandomHeroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
        initSwipeRefresh()
    }

    //Coloca los colores al Swipe Refresh Layout
    private fun initSwipeRefresh() {
        binding.sfRandomHero.setColorSchemeResources(R.color.secondary)
        binding.sfRandomHero.setProgressBackgroundColorSchemeResource(R.color.primaryDark)
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                randomHeroViewModel.state.collect {
                    when (it) {
                        RandomHeroState.Loading -> loadingState()
                        RandomHeroState.Error -> errorState()
                        RandomHeroState.Hidden -> hiddenState()
                        is RandomHeroState.Reveal -> revealState(it)
                    }
                }
            }
        }
    }

    //Coloca los datos del Héroe en la vista
    private fun revealState(state: RandomHeroState.Reveal) {
        binding.tvIdRandom.text = getString(R.string.tvIdRandomText).plus(state.hero.id)
        binding.tvNameRandom.text = state.hero.name
        binding.tvPublisherRandom.text = state.hero.biography.publisher

        Glide
            .with(this)
            .load(state.hero.image.url)
            .placeholder(R.drawable.signo_interrogacion)
            .into(binding.ivAvatarRandom)
    }

    //Muestra un mensaje de Error si la petición a la API falla
    private fun errorState() {
        binding.sfRandomHero.isRefreshing = false
        binding.tvNameRandom.isVisible = false
        binding.tvPublisherRandom.isVisible = false

        binding.tvIdRandom.text = getString(R.string.tvTitleResultTextError)
        binding.ivAvatarRandom.setImageResource(R.drawable.pagina_no_encontrada)
    }

    //Oculta los datos del Héroe en la vista
    private fun hiddenState() {
        binding.sfRandomHero.isRefreshing = false
        binding.tvNameRandom.isVisible = true
        binding.tvPublisherRandom.isVisible = true

        binding.ivAvatarRandom.setImageResource(R.drawable.signo_interrogacion)
        binding.tvIdRandom.text = getString(R.string.tvIdRandomText).plus(getString(R.string.tvRandomHeroText))
        binding.tvNameRandom.text = getString(R.string.tvRandomHeroText)
        binding.tvPublisherRandom.text = getString(R.string.tvRandomHeroText)
    }

    //Estado de carga de datos
    private fun loadingState() {
        binding.sfRandomHero.isRefreshing = true
    }

    private fun initListeners() {
        binding.cvAvatarRandom.setOnClickListener {
            when (randomHeroViewModel.state.value) {
                is RandomHeroState.Hidden -> spinCard()
                is RandomHeroState.Reveal -> findNavController().navigate(RandomHeroFragmentDirections.actionRandomHeroFragmentToDetailHeroFragment(randomHeroViewModel.data.value!!))
                else -> {
                    //
                }
            }
        }
        binding.sfRandomHero.setOnRefreshListener { randomHeroViewModel.getNewRandomHero() }
    }

    //Animación girar en el eje Y del CardView
    private fun spinCard() {
        val animator = ObjectAnimator.ofFloat(binding.cvAvatarRandom, View.ROTATION_Y, 0f, 1440f)
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnStart { randomHeroViewModel.getRevealRandomHero() }
        animator.start()
    }
}