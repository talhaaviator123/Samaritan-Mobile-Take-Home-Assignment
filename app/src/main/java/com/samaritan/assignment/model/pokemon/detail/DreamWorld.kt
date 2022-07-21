package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DreamWorld(

    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null

): Serializable