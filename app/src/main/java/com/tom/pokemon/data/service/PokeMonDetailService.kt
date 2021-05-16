package com.tom.pokemon.data.service

import com.tom.pokemon.data.model.PokeMonDetailResponse
import com.tom.pokemon.domain.entity.PokeMonDetail
import retrofit2.http.GET
import retrofit2.http.Path


interface PokeMonDetailService {
    @GET("{id}")
    suspend fun getPokeMonDetail(@Path("id") type: Int) : PokeMonDetailResponse
}