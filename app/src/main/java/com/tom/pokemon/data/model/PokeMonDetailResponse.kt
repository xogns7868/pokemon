package com.tom.pokemon.data.model

import com.google.gson.annotations.SerializedName
import com.tom.pokemon.domain.entity.PokeMonDetail
import kotlinx.android.parcel.RawValue

data class PokeMonDetailResponse(
        @SerializedName("id")
        val id: Int,

        @SerializedName("height")
        val height: String? = null,

        @SerializedName("weight")
        val weight: String? = null,

        @SerializedName("sprites")
        val sprites: Sprite? = null,

        var sprite: String? = null,

        var location: @RawValue List<PokeMonLocation>? = null,

        @SerializedName("name")
        val name: String
)

data class Sprite (
        @SerializedName("back_default")
        val back_default: String?,

        @SerializedName("back_female")
        val back_female: String?,

        @SerializedName("back_shiny")
        val back_shiny: String?,

        @SerializedName("back_shiny_female")
        val back_shiny_female: String?,

        @SerializedName("front_default")
        val front_default: String?,

        @SerializedName("front_female")
        val front_female: String?,

        @SerializedName("front_shiny")
        val front_shiny: String,

        @SerializedName("front_shiny_female")
        val front_shiny_female: String?
)