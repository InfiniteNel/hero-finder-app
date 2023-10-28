package com.jroslar.heroapp.data.network

import com.jroslar.heroapp.data.network.response.ListHeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApiService {
    @GET("{name}")
    suspend fun getListHeroByName(@Path("name") heroName:String): ListHeroResponse
}