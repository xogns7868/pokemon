package com.tom.pokemon.data.local

import com.tom.pokemon.data.model.PokeMonResponse

class PokeMonNameCache {
    var pokeMonList : PokeMonResponse? = null

    fun getName() : PokeMonResponse? = pokeMonList
    fun setName(pokeMonList: PokeMonResponse){
        this.pokeMonList = pokeMonList
    }
}