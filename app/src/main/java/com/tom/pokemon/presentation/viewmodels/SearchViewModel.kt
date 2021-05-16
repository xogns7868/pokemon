package com.tom.pokemon.presentation.viewmodels

import androidx.lifecycle.*
import com.tom.pokemon.data.handler.Resource
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.domain.usecase.GetPokeMonDetail
import com.tom.pokemon.domain.usecase.MakeSearchList
import kotlinx.coroutines.launch

class SearchViewModel (
    private val makeSearchList: MakeSearchList,
    private val getPokeMonDetail: GetPokeMonDetail
) : ViewModel() {
    private val _pokeMonList = MutableLiveData<Resource<List<PokeMonDetail>>>()
    private val _pokeMonDetail = MutableLiveData<Resource<PokeMonDetail>>()

    val pokeMonList: LiveData<Resource<List<PokeMonDetail>>> = _pokeMonList
    val pokeMonDetail: LiveData<Resource<PokeMonDetail>> = _pokeMonDetail

    fun searchPokeMons(searchText: String){
        viewModelScope.launch {
            _pokeMonList.postValue(makeSearchList(searchText))
        }
    }

    fun selectPokeMon(id: Int, name: String){
        viewModelScope.launch {
            _pokeMonDetail.postValue(getPokeMonDetail(id, name))
        }
    }
}