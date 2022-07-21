package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonDetail(

    @SerializedName("name") var name: String? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("types") var types: ArrayList<Types> = arrayListOf(),
    @SerializedName("stats") var stats: ArrayList<Stats> = arrayListOf(),
    @SerializedName("sprites") var sprites: Sprites? = Sprites()

): Serializable