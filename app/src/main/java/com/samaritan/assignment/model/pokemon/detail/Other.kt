package com.samaritan.assignment.model.pokemon.detail

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Other(

    @SerializedName("dream_world") var dreamWorld: DreamWorld? = DreamWorld(),
    @SerializedName("home") var home: Home? = Home(),
    @SerializedName("official-artwork") var officialArtwork: OfficialArtwork? = OfficialArtwork()

): Serializable