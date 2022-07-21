package com.samaritan.assignment.repository

import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import com.samaritan.assignment.model.Resource
import com.samaritan.assignment.model.pokemon.PokemonList


interface MainRepository {
    suspend fun getPokemonList(queryParams: Map<String, String>): Resource<PokemonList>
    suspend fun getPokemonDetails(id:String): Resource<PokemonDetail>
}