package com.tom.pokemon.presentation.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.tom.pokemon.domain.entity.PokeMonDetail
import org.koin.java.KoinJavaComponent.getKoin

class MyFragmentFactory(private val pokeMonDetail: PokeMonDetail) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className){
            MapFragment::class.java.name -> MapFragment(pokeMonDetail)
            else -> super.instantiate(classLoader, className)
        }
    }
}