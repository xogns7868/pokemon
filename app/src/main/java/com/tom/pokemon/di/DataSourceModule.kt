package com.tom.pokemon.di

import com.tom.pokemon.data.remote.RemoteDataSource
import com.tom.pokemon.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module{
    single<RemoteDataSource> {
        RemoteDataSourceImpl(
                pokeMonService = get(),
                pokeMonNameCache = get(),
                pokeMonDetailService = get())
    }
}