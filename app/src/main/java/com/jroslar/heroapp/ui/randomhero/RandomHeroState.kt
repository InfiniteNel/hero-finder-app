package com.jroslar.heroapp.ui.randomhero

import com.jroslar.heroapp.domain.model.HeroModel

sealed class RandomHeroState {
    data object Loading: RandomHeroState()
    data object Error: RandomHeroState()
    data object Hidden: RandomHeroState()
    data class Reveal(val hero: HeroModel): RandomHeroState()
}