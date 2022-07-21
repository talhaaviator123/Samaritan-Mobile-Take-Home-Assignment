package com.samaritan.assignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ztbl.db.room.lovs.PokemonsDao
import com.samaritan.assignment.model.PokemonTable
import com.samaritan.assignment.model.pokemon.PokemonListItem


@Database(
    entities = [PokemonTable::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun PokemonsDao(): PokemonsDao
}