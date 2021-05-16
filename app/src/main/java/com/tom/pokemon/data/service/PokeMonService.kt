package com.tom.pokemon.data.service

import com.tom.pokemon.data.model.PokeMonLocationResponse
import com.tom.pokemon.data.model.PokeMonResponse
import retrofit2.http.GET

interface PokeMonService {

    @GET("pokemon_name")
    suspend fun getPokeMons() : PokeMonResponse

    @GET("/pokemon_locations")
    suspend fun getLocations(): PokeMonLocationResponse
}