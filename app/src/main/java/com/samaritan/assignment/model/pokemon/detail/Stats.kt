package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Stats(

    @SerializedName("base_stat") var baseStat: Int? = null,
    @SerializedName("effort") var effort: Int? = null,
    @SerializedName("stat") var stat: Stat? = Stat()

):Serializable
