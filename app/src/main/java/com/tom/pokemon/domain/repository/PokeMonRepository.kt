package com.tom.pokemon.domain.repository

import com.tom.pokemon.data.handler.Resource
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.data.model.PokeMonLocationResponse
import com.tom.pokemon.data.model.PokeMonResponse

interface PokeMonRepository {

    suspend fun getPokeMons() : PokeMonResponse
    suspend fun getPokeMonLocationList(): PokeMonLocationResponse
    suspend fun getPokeMonDetail(id: Int) : PokeMonDetail
}