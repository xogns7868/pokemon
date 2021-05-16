package com.tom.pokemon.di

import com.tom.pokemon.presentation.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { SearchViewModel(makeSearchList = get(), getPokeMonDetail = get()/*, getPokeMonLocation = get()*/) }
}