package com.jroslar.heroapp.domain.usecase

import com.jroslar.heroapp.data.HeroRepositoryImpl
import javax.inject.Inject

class GetListHeroByNameUseCase @Inject constructor(private val repositoryImpl: HeroRepositoryImpl) {
    suspend operator fun invoke(heroName: String) = repositoryImpl.getListHero(heroName)
}