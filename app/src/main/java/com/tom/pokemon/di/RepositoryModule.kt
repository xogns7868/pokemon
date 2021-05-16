package com.tom.pokemon.di

import com.tom.pokemon.domain.repository.PokeMonRepository
import com.tom.pokemon.data.repository.PokeMonRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module{
    single<PokeMonRepository> { PokeMonRepositoryImpl(remoteDataSource = get()) }
}