package com.tom.pokemon.di

import com.tom.pokemon.data.local.PokeMonNameCache
import com.tom.pokemon.data.service.PokeMonDetailService
import com.tom.pokemon.data.service.PokeMonService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.dsl.module


private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val BASE_URL_POKEMON = "https://demo0928971.mockable.io/"
private const val BASE_URL_POKEMON_DETAIL = "https://pokeapi.co/api/v2/pokemon/"

val networkModule = module {

    single {
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL_POKEMON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeMonService::class.java)
    }


    single {
        Retrofit.Builder()
                .client(get<OkHttpClient>())
                .baseUrl(BASE_URL_POKEMON_DETAIL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeMonDetailService::class.java)
    }

    single {
        PokeMonNameCache()
    }

    //error exception

}