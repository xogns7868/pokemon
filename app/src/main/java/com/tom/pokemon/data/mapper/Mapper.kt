package com.tom.pokemon.data.mapper

interface Mapper<E, D>{
    fun mapToEntity(type: D) : E
}