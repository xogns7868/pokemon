package com.tom.pokemon.di

import com.tom.pokemon.data.local.PokeMonNameCache
import com.tom.pokemon.data.remote.RemoteDataSource
import com.tom.pokemon.data.remote.RemoteDataSourceImpl
import com.tom.pokemon.data.service.PokeMonDetailService
import com.tom.pokemon.data.service.PokeMonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.koin.dsl.module
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRemoteDataSource(
        pokeMonService: PokeMonService,
        pokeMonNameCache: PokeMonNameCache,
        pokeMonDetailService: PokeMonDetailService): RemoteDataSource =
        RemoteDataSourceImpl(pokeMonService, pokeMonNameCache, pokeMonDetailService)
}