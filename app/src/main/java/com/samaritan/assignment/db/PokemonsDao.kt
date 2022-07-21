package com.example.ztbl.db.room.lovs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samaritan.assignment.model.PokemonTable
import com.samaritan.assignment.model.pokemon.PokemonListItem


@Dao
interface PokemonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetPokemon(pokemonTable: PokemonTable): Long?

    @Query("SELECT * FROM Pokemon")
    fun  getPokemonList():io.reactivex.Single<List<PokemonTable>>

}