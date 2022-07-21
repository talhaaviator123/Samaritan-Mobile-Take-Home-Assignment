package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Type(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

): Serializable