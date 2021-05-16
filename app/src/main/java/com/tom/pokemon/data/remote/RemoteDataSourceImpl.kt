package com.tom.pokemon.data.remote

import com.tom.pokemon.data.local.PokeMonNameCache
import com.tom.pokemon.data.model.PokeMonDetailResponse
import com.tom.pokemon.data.model.PokeMonLocationResponse
import com.tom.pokemon.data.service.PokeMonDetailService
import com.tom.pokemon.data.service.PokeMonService
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.data.model.PokeMonResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val pokeMonService: PokeMonService,
    private val pokeMonNameCache: PokeMonNameCache,
    private val pokeMonDetailService: PokeMonDetailService
) : RemoteDataSource {

    override suspend fun getPokeMons() : PokeMonResponse {
        if(pokeMonNameCache.getName() == null){
            pokeMonNameCache.setName(pokeMonService.getPokeMons())
        }
        return pokeMonNameCache.getName() ?: PokeMonResponse(emptyList())
    }

    override suspend fun getPokeMonLocationList(): PokeMonLocationResponse {
        return pokeMonService.getLocations()
    }

    override suspend fun getPokeMonDetail(id: Int): PokeMonDetailResponse =
            pokeMonDetailService.getPokeMonDetail(id).apply {
                sprite = sprites?.let {
                    it.front_default ?: it.front_female ?: it.front_shiny_female ?: it.front_shiny
                    ?: it.back_default ?: it.back_female ?: it.back_shiny ?: it.back_shiny_female
                }
            }
}