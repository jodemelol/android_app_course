package com.chivata.app1.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/6436690919752510/search/{name}")
    suspend fun getSuperHeroe(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("https://superheroapi.com/api/6436690919752510/{id}")
    suspend fun getSuperHeroDetail(@Path("id") SuperHeroId: String): Response<SuperHeroDetailResponse>

}