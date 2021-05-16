package com.tom.pokemon.di

import com.tom.pokemon.data.handler.ResponseHandler
import com.tom.pokemon.data.repository.PokeMonRepositoryImpl
import com.tom.pokemon.domain.repository.PokeMonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HandlerModule {
    @Singleton
    @Provides
    fun provideResponseHandler() = ResponseHandler()
}