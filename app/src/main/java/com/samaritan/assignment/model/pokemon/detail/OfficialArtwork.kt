package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class OfficialArtwork (

  @SerializedName("front_default" ) var frontDefault : String? = null

): Serializable