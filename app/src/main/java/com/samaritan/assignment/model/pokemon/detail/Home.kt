package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Home (

  @SerializedName("front_default"      ) var frontDefault     : String? = null,
  @SerializedName("front_female"       ) var frontFemale      : String? = null,
  @SerializedName("front_shiny"        ) var frontShiny       : String? = null,
  @SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null

): Serializable