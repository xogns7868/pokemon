package com.tom.pokemon.domain.usecase

import com.tom.pokemon.data.handler.Resource
import com.tom.pokemon.data.handler.ResponseHandler
import com.tom.pokemon.domain.repository.PokeMonRepository
import com.tom.pokemon.domain.entity.PokeMonDetail
import java.lang.Exception

class GetPokeMonDetail(
    private val pokeMonRepository: PokeMonRepository,
    private val responseHandler: ResponseHandler
) {
    suspend operator fun invoke(id: Int, name: String): Resource<PokeMonDetail> {
        return try{
            responseHandler.handleSuccess(pokeMonRepository.getPokeMonDetail(id).apply {
                this.name = name
                this.location =
                    pokeMonRepository.getPokeMonLocationList().pokeMonLocation.filter { it.id == id }
            })
            } catch (e: Exception){
            responseHandler.handleException(e)
        }
    }
}