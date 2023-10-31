package com.jroslar.heroapp.data.network

import com.jroslar.heroapp.data.network.response.HeroResponse
import com.jroslar.heroapp.data.network.response.ListHeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApiService {
    @GET("search/{name}")
    suspend fun getListHeroByName(@Path("name") heroName:String): ListHeroResponse

    @GET("{id}")
    suspend fun getHeroById(@Path("id") heroId: Int): HeroResponse
}