package com.tom.pokemon.domain.usecase

import com.tom.pokemon.data.handler.Resource
import com.tom.pokemon.data.handler.ResponseHandler
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.domain.repository.PokeMonRepository
import java.lang.Exception

class MakeSearchList(
    private val pokeMonRepository: PokeMonRepository,
    private val responseHandler: ResponseHandler
) {
    suspend operator fun invoke(searchText: String) : Resource<List<PokeMonDetail>> {
        return try {
            responseHandler.handleSuccess(pokeMonRepository.getPokeMons().pokemon
                .fold(emptyList()) { acc, pokeMon ->
                    val tempList = ArrayList<PokeMonDetail>()
                    pokeMon.names.map{ currentPokeMonName ->
                        if(currentPokeMonName.startsWith(searchText, true))
                            tempList.add(PokeMonDetail(pokeMon.id, name = currentPokeMonName))
                    }
                    acc + tempList
                })
        }   catch (e: Exception){
            responseHandler.handleException(e)
        }
    }
}