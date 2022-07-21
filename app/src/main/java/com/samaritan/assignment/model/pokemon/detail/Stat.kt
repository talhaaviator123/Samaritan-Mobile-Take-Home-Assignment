package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Stat (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

):Serializable