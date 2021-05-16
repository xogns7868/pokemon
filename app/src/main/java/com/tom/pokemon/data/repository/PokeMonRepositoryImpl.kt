package com.tom.pokemon.data.repository

import com.tom.pokemon.data.handler.Resource
import com.tom.pokemon.data.handler.ResponseHandler
import com.tom.pokemon.data.mapper.PokeMonMapper
import com.tom.pokemon.data.remote.RemoteDataSource
import com.tom.pokemon.domain.repository.PokeMonRepository
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.data.model.PokeMonLocationResponse
import com.tom.pokemon.data.model.PokeMonResponse
import java.lang.Exception

class PokeMonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : PokeMonRepository {

    private val pokeMonMapper = PokeMonMapper

    override suspend fun getPokeMons(): PokeMonResponse {
        return remoteDataSource.getPokeMons()
    }

    override suspend fun getPokeMonLocationList(): PokeMonLocationResponse {
        return remoteDataSource.getPokeMonLocationList()
    }

    override suspend fun getPokeMonDetail(id: Int): PokeMonDetail {
        return pokeMonMapper.mapToEntity(remoteDataSource.getPokeMonDetail(id))
    }
}