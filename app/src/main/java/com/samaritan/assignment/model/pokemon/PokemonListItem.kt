package com.samaritan.assignment.model.pokemon

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.samaritan.assignment.model.pokemon.detail.PokemonCapturedModel
import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import java.io.Serializable

data class PokemonListItem(

    var name: String? = null,
    var backgroundColor: Int = 0,
    var url: String? = null,
    var pokemonId: String? = null,
    var typesString: String? = null,
    var pokemonDetail: PokemonDetail? = null,
    var pokemonCapturedModel: PokemonCapturedModel? = null


) : Serializable