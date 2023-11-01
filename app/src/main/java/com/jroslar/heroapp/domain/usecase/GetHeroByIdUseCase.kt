package com.jroslar.heroapp.domain.usecase

import com.jroslar.heroapp.data.HeroRepositoryImpl
import javax.inject.Inject

class GetHeroByIdUseCase @Inject constructor(private val repositoryImpl: HeroRepositoryImpl) {
    suspend operator fun invoke(heroId: Int) = repositoryImpl.getHeroById(heroId)
}