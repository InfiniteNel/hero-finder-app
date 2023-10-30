package com.jroslar.heroapp.ui.searchhero

import com.jroslar.heroapp.domain.model.HeroModel

sealed class SearchHeroState {
    data object Loading: SearchHeroState()
    data object Error: SearchHeroState()
    data class NoData(val heroList:List<HeroModel>): SearchHeroState()
    data class Success(val heroList:List<HeroModel>): SearchHeroState()
}