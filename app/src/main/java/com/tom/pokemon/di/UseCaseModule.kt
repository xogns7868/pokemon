package com.tom.pokemon.di

import com.tom.pokemon.data.handler.ResponseHandler
import com.tom.pokemon.domain.repository.PokeMonRepository
import com.tom.pokemon.domain.usecase.GetPokeMonDetail
import com.tom.pokemon.domain.usecase.MakeSearchList
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.koin.dsl.module

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @ActivityRetainedScoped
    @Provides
    fun provideMakeSearchList(
        pokeMonRepository: PokeMonRepository,
        responseHandler: ResponseHandler
    ) = MakeSearchList(pokeMonRepository, responseHandler)

    @ActivityRetainedScoped
    @Provides
    fun provideGetPokeMonDetail(
        pokeMonRepository: PokeMonRepository,
        responseHandler: ResponseHandler
    ) = GetPokeMonDetail(pokeMonRepository, responseHandler)

}