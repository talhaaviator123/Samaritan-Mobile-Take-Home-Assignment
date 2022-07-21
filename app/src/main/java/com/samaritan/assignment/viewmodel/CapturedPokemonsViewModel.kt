package com.samaritan.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ztbl.db.room.lovs.PokemonsDao
import com.example.ztbl.db.room.lovs.PokemonsDataSource
import com.google.gson.Gson
import com.samaritan.assignment.model.ApiEvent
import com.samaritan.assignment.model.PokemonTable
import com.samaritan.assignment.model.Resource
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.repository.MainRepository
import com.samaritan.assignment.view.adapter.PokemonsAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CapturedPokemonsViewModel @Inject constructor(
    private val repository: MainRepository,
    private val pokemonDao: PokemonsDao
) : ViewModel(),
    PokemonsDataSource.CapturedPokemonListInterface {

    private var limit: Int = 20
    private var offset: Int = 0
    var pokemonsList: ArrayList<PokemonListItem> = ArrayList()
    lateinit var list: List<Long>
    private val _capturedPokemons = MutableStateFlow<ArrayList<PokemonListItem>>(ArrayList())
    val capturedPokemons: StateFlow<ArrayList<PokemonListItem>> = _capturedPokemons
    var pokemonsDataSource: PokemonsDataSource = PokemonsDataSource(pokemonDao)


    fun getCapturedPokemonsList() {
        pokemonsDataSource.getCapturedPokemonsList(this)
    }

    override fun getLoanResponseList(pokemonList: ArrayList<PokemonListItem>) {
        _capturedPokemons.value=pokemonList
    }

}