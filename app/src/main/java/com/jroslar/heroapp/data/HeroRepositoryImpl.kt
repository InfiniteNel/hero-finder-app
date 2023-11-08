package com.jroslar.heroapp.data

import android.util.Log
import com.jroslar.heroapp.data.network.heroapi.HeroApiService
import com.jroslar.heroapp.data.network.heroapi.response.toModelHero
import com.jroslar.heroapp.domain.HeroRepository
import com.jroslar.heroapp.domain.model.HeroModel
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(private val apiService: HeroApiService): HeroRepository {

    override suspend fun getListHero(heroName: String): List<HeroModel>? {
        runCatching { apiService.getListHeroByName(heroName) }
            .onSuccess {
                return if (it.results != null) {
                    it.results.map { response -> response.toModelHero() }
                } else {
                    emptyList()
                }
            }
            .onFailure { Log.i("HeroFinder", "Error: ${it.message}") }

        return null
    }

    override suspend fun getHeroById(heroId: Int): HeroModel? {
        kotlin.runCatching { apiService.getHeroById(heroId) }
            .onSuccess {
                if (it.id != null) {
                    return it.toModelHero()
                }
            }
            .onFailure { Log.i("HeroFinder", "Error: ${it.message}") }

        return null
    }
}