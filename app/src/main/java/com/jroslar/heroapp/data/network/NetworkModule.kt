package com.jroslar.heroapp.data.network

import com.jroslar.heroapp.BuildConfig.BASE_URL
import com.jroslar.heroapp.data.HeroRepositoryImpl
import com.jroslar.heroapp.data.network.heroapi.HeroApiService
import com.jroslar.heroapp.domain.HeroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHeroApiService(retrofit: Retrofit): HeroApiService {
        return retrofit.create(HeroApiService::class.java)
    }

    @Provides
    fun provideHeroRepository(apiService: HeroApiService): HeroRepository {
        return HeroRepositoryImpl(apiService)
    }
}