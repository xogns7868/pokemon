package com.tom.pokemon.di

import com.tom.pokemon.data.local.PokeMonNameCache
import com.tom.pokemon.data.service.PokeMonDetailService
import com.tom.pokemon.data.service.PokeMonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.dsl.module
import javax.inject.Singleton


private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val BASE_URL_POKEMON = "https://demo0928971.mockable.io/"
private const val BASE_URL_POKEMON_DETAIL = "https://pokeapi.co/api/v2/pokemon/"

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL_POKEMON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePokeMonService(retrofit: Retrofit): PokeMonService =
        retrofit.create(PokeMonService::class.java)

    @Singleton
    @Provides
    fun providePokeMonDetailService(retrofit: Retrofit): PokeMonDetailService =
        retrofit.create(PokeMonDetailService::class.java)

    @Singleton
    @Provides
    fun providePokeMonNameCache() = PokeMonNameCache()
}