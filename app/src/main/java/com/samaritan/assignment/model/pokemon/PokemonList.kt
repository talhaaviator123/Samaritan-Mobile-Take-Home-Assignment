package com.samaritan.assignment.model.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonList(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<PokemonListItem> = arrayListOf()

)