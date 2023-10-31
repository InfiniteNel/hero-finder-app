package com.jroslar.heroapp.domain

import com.jroslar.heroapp.domain.model.HeroModel

interface HeroRepository {
    suspend fun getListHero(heroName:String): List<HeroModel>?
    suspend fun getHeroById(heroId: Int): HeroModel?
}