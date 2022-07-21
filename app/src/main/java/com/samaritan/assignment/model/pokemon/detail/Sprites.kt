package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import com.samaritan.assignment.model.pokemon.detail.Other
import java.io.Serializable


data class Sprites(

    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_female") var backFemale: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null,
    @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
    @SerializedName("other") var other: Other? = Other(),

    ): Serializable