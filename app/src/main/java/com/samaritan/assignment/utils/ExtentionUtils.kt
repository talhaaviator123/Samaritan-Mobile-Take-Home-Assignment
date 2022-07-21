package com.samaritan.assignment.utils

import com.samaritan.assignment.model.pokemon.detail.Types

fun ArrayList<Types>.getString() : String{
    return this.joinToString(separator = " - ", limit = -1, transform = { it.type?.name!! })
}
