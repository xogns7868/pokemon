package com.tom.pokemon

import android.app.Application
import com.tom.pokemon.di.*
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

@HiltAndroidApp
class PokeMonApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}