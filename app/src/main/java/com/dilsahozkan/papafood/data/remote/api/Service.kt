package com.dilsahozkan.papafood.data.remote.api

import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {
    @GET("recipes/random")
    suspend fun randomRecipes(@Query("number") number: Int,
                              @Query("tags") tags: String,
                              @Query("limitLicense") limitLicense: Boolean,
                              @Query("apiKey") apiKey: String)
            : Response<RandomRecipe>
}