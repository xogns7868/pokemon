package com.tom.pokemon.di

import com.tom.pokemon.data.handler.ResponseHandler
import com.tom.pokemon.data.repository.PokeMonRepositoryImpl
import com.tom.pokemon.domain.repository.PokeMonRepository
import org.koin.dsl.module

val handlerModule = module{
    single<ResponseHandler> { ResponseHandler()}
}