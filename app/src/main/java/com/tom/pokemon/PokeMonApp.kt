package com.tom.pokemon

import android.app.Application
import com.tom.pokemon.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class PokeMonApp : Application(){

    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidContext(this@PokeMonApp)
//            fragmentFactory()
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(networkModule)
            modules(handlerModule)
            modules(useCaseModule)
//            modules(fragmentModule)
            modules(viewModelModule)
        }
    }

}