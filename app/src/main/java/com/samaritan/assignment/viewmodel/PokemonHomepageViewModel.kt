package com.samaritan.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ztbl.db.room.lovs.PokemonsDao
import com.samaritan.assignment.model.ApiEvent
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
class PokemonHomepageViewModel @Inject constructor(
    private val repository: MainRepository,
    private val pokemonsDao: PokemonsDao
) : ViewModel() {

    private var limit: Int = 20
    private var offset: Int = 0
    var pokemonsList: ArrayList<PokemonListItem> = ArrayList()
    lateinit var list: List<Long>
    private val _conversion = MutableStateFlow<ApiEvent>(ApiEvent.Empty)
    val conversion: StateFlow<ApiEvent> = _conversion

    fun getPokemonList() {
        val queryParams = HashMap<String, String>()
        queryParams["limit"] = limit.toString()
        queryParams["offset"] = offset.toString()
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = ApiEvent.Loading
            when (val response = repository.getPokemonList(queryParams)) {
                is Resource.Error -> _conversion.value = ApiEvent.Failure(response.message!!)
                is Resource.Success -> {
                    offset += limit
                    val data = response.data!!
                    addUpdateDataToList(data.results)
                    _conversion.value = ApiEvent.Success
                }
            }
        }
    }

    fun getPokemonDetails(id: String, callback: PokemonsAdapter.PokemonDetailCallback) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getPokemonDetails(id)) {
                is Resource.Success -> {
                    launch(Dispatchers.Main) { callback.onPokemonDetailFetchSuccess(response.data!!) }

                }
            }
        }
    }

    fun addUpdateDataToList(data: ArrayList<PokemonListItem>) {
        data.forEachIndexed { index, pokemonListItem ->
            val fetchIdFromUrlArray = pokemonListItem.url?.split("/") //fetching pokemon id from
            fetchIdFromUrlArray?.let {
                pokemonListItem.pokemonId = it[it.size - 2]
            }
        }
        pokemonsList.addAll(data)
    }

}