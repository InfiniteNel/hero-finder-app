package com.jroslar.heroapp.ui.randomhero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jroslar.heroapp.core.Constant.MAX_NUMBER_HERO
import com.jroslar.heroapp.domain.model.HeroModel
import com.jroslar.heroapp.domain.usecase.GetHeroByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class RandomHeroViewModel  @Inject constructor(private val getHeroByIdUseCase: GetHeroByIdUseCase) : ViewModel() {

    private var _state = MutableStateFlow<RandomHeroState>(RandomHeroState.Loading)
    val state: StateFlow<RandomHeroState> = _state

    private var data: HeroModel? = null

    init {
        getNewRandomHero()
    }

    //Petición a la API de un nuevo Héroe aleatorio
    fun getNewRandomHero() {
        viewModelScope.launch {
            _state.value = RandomHeroState.Loading
            val random = Random()
            val result = withContext(Dispatchers.IO) {getHeroByIdUseCase(random.nextInt(MAX_NUMBER_HERO)+1) }

            if (result == null) {
                _state.value = RandomHeroState.Error
            } else {
                _state.value = RandomHeroState.Hidden
                data = result
            }
        }
    }

    //Revela el Héroe en la vista
    fun getRevealRandomHero() {
        if (data == null) {
            _state.value = RandomHeroState.Error
        } else {
            _state.value = RandomHeroState.Reveal(data!!)
        }
    }
}