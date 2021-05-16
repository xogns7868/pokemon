package com.tom.pokemon.data.mapper

import com.tom.pokemon.data.model.PokeMonDetailResponse
import com.tom.pokemon.domain.entity.PokeMonDetail

object PokeMonMapper : Mapper<PokeMonDetail, PokeMonDetailResponse>{
    override fun mapToEntity(type: PokeMonDetailResponse): PokeMonDetail {
        return PokeMonDetail(
            id = type.id,
            height = type.height,
            weight = type.weight,
            sprite = type.sprite,
            location = type.location,
            name = type.name
        )
    }
}