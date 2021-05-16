package com.tom.pokemon.di

import com.tom.pokemon.data.remote.RemoteDataSource
import com.tom.pokemon.domain.repository.PokeMonRepository
import com.tom.pokemon.data.repository.PokeMonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.koin.dsl.module
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @ActivityRetainedScoped
    @Provides
    fun providePokeMonRepository(remoteDataSource: RemoteDataSource): PokeMonRepository =
        PokeMonRepositoryImpl(remoteDataSource)

}