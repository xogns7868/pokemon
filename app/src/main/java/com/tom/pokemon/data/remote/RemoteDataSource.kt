package com.tom.pokemon.data.remote

import com.tom.pokemon.data.model.PokeMonDetailResponse
import com.tom.pokemon.data.model.PokeMonLocationResponse
import com.tom.pokemon.data.model.PokeMonResponse

interface RemoteDataSource {
    suspend fun getPokeMons() : PokeMonResponse
    suspend fun getPokeMonLocationList(): PokeMonLocationResponse
    suspend fun getPokeMonDetail(id: Int) : PokeMonDetailResponse
}