package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import com.samaritan.assignment.model.pokemon.detail.Type
import java.io.Serializable

data class Types(

    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: Type? = Type()

): Serializable