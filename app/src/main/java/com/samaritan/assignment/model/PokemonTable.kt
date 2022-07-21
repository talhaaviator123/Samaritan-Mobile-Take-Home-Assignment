package com.samaritan.assignment.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Pokemon")
data class PokemonTable(
    @NonNull
    @PrimaryKey
    var name: String = "",
    var json: String=""
)