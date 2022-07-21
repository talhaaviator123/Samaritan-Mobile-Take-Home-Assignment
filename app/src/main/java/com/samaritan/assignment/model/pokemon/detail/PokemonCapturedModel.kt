package com.samaritan.assignment.model.pokemon.detail

import java.io.Serializable

data class PokemonCapturedModel(

    var nickName: String? = null,
    var capturedDate: String? = null,
    var capturedLevel: String? = null,

): Serializable