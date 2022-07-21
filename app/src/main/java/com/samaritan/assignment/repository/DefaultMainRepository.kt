package com.samaritan.assignment.repository

import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import com.samaritan.assignment.model.api.PokemonApi
import com.samaritan.assignment.model.Resource
import com.samaritan.assignment.model.pokemon.PokemonList
import java.lang.Exception
import javax.inject.Inject


class DefaultMainRepository @Inject constructor(
    val pokemonApi: PokemonApi
):MainRepository {
    override suspend fun getPokemonList(queryParams: Map<String, String>): Resource<PokemonList> {
        return  try {
            val response = pokemonApi.getPokemonList(queryParams)

            val serverResponse = response.body()
            if (response.code() == 200 && serverResponse != null) {
                Resource.Success(serverResponse)
            } else {
                Resource.Error("An Error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("An $e occurred")
        }
    }

    override suspend fun getPokemonDetails(id:String): Resource<PokemonDetail> {
        return  try {
            val response = pokemonApi.getPokemonDetails(id)

            val serverResponse = response.body()
            if (response.code() == 200 && serverResponse != null) {
                Resource.Success(serverResponse)
            } else {
                Resource.Error("An Error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("An $e occurred")
        }
    }

}