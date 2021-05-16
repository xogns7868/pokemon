package com.tom.pokemon.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tom.pokemon.data.model.PokeMonLocation
import com.tom.pokemon.data.model.Sprite
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PokeMonDetail(
        val id: Int,
        val height: String? = null,
        val weight: String? = null,
        var sprite: String? = null,
        var location: @RawValue List<PokeMonLocation>? = null,
        var name: String
): Parcelable
