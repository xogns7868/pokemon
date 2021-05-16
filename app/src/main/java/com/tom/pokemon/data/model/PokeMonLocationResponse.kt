package com.tom.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokeMonLocationResponse(
    @SerializedName("pokemons")
    val pokeMonLocation: List<PokeMonLocation>
)

data class PokeMonLocation(
        @SerializedName("lat")
        val lat: Double,

        @SerializedName("lng")
        val lng: Double,

        @SerializedName("id")
        val id: Int
)