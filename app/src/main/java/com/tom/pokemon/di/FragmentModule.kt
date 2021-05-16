package com.tom.pokemon.di

import androidx.fragment.app.DialogFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module


val fragmentModule = module{
    fragment { DialogFragment() }
}