package com.samaritan.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ztbl.db.room.lovs.PokemonsDao
import com.example.ztbl.db.room.lovs.PokemonsDataSource
import com.samaritan.assignment.dependencyInjection.API_KEY
import com.samaritan.assignment.model.ApiEvent
import com.samaritan.assignment.model.Resource
import com.samaritan.assignment.model.observers.PokemonDetailsObserverModel
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.model.pokemon.detail.PokemonCapturedModel
import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import com.samaritan.assignment.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
    private val pokemonDao: PokemonsDao
) : ViewModel() {

    val pokemonDetailsObserverModel = PokemonDetailsObserverModel()

    var pokemonListItem: PokemonListItem? = null

    var pokemonsDataSource: PokemonsDataSource = PokemonsDataSource(pokemonDao)


    fun addPokemon() {
        val pokemonCapturedModel = PokemonCapturedModel()
        pokemonCapturedModel.nickName = pokemonDetailsObserverModel.nickname
        pokemonCapturedModel.capturedDate = pokemonDetailsObserverModel.capturedDate
        pokemonCapturedModel.capturedLevel = pokemonDetailsObserverModel.capturedLevel
        pokemonListItem?.pokemonCapturedModel = pokemonCapturedModel

        pokemonsDataSource.addPokemonInDatabase(pokemonListItem!!)
    }


}