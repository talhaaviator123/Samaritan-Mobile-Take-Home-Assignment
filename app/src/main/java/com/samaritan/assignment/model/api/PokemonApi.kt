package com.samaritan.assignment.model.api

import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import com.samaritan.assignment.model.pokemon.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(@QueryMap queryParams: Map<String, String>): Response<PokemonList>
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path(value = "id", encoded = true) id: String): Response<PokemonDetail>
}