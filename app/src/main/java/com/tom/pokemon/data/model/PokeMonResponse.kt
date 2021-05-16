package com.tom.pokemon.data.model

import com.google.gson.annotations.SerializedName


data class PokeMonResponse(
        @SerializedName("pokemons")
        val pokemon: List<PokeMon>
)

data class PokeMon(
        @SerializedName("id")
        val id: Int,

        @SerializedName("names")
        val names: List<String>
)