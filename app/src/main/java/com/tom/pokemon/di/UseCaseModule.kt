package com.tom.pokemon.di

import com.tom.pokemon.domain.usecase.GetPokeMonDetail
import com.tom.pokemon.domain.usecase.MakeSearchList
import org.koin.dsl.module


val useCaseModule = module{
    factory { MakeSearchList(pokeMonRepository = get(), responseHandler = get()) }
    factory { GetPokeMonDetail(pokeMonRepository = get(), responseHandler = get()) }
//    factory { GetPokeMonLocation(pokeMonRepository = get()) }
}