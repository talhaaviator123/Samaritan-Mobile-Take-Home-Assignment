package com.example.ztbl.db.room.lovs

import android.util.Log
import com.google.gson.Gson
import com.samaritan.assignment.model.PokemonTable
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.viewmodel.CapturedPokemonsViewModel
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PokemonsDataSource @Inject constructor(private val pokemonsDao: PokemonsDao) {


    fun addPokemonInDatabase(pokemon: PokemonListItem): Long {
        val pokemonTable = PokemonTable()
           pokemonTable.name = pokemon.name!!
        pokemonTable.json = Gson().toJson(pokemon).toString()
        return pokemonsDao.insetPokemon(pokemonTable)!!
    }


    fun getCapturedPokemonsList(capturedPokemonListInterface: CapturedPokemonListInterface) {
        //this.capturedPokemonListInterface = capturedPokemonListInterface

        var loanDBModelList = pokemonsDao.getPokemonList()
        loanDBModelList.subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<List<PokemonTable>> {
            override fun onSuccess(t: List<PokemonTable>) {
                val pokemonList = ArrayList<PokemonListItem>()
                if (!t.isEmpty()) {
                    for (item in t as ArrayList<PokemonTable>) {
                        pokemonList.add(
                            Gson().fromJson(
                                item.json, PokemonListItem::class.java
                            )
                        )
                    }
                }
                capturedPokemonListInterface.getLoanResponseList(pokemonList)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                val loanResponseList = ArrayList<PokemonListItem>()
                capturedPokemonListInterface.getLoanResponseList(loanResponseList)
            }

        })
    }


    interface CapturedPokemonListInterface {
        fun getLoanResponseList(pokemonList: ArrayList<PokemonListItem>)
    }

}
