package com.jroslar.heroapp.ui.favhero

sealed class FavHeroState {
    data object NoData: FavHeroState()
    data class Success(val email: String?, val userName: String?): FavHeroState()
}