package com.samaritan.assignment.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.samaritan.assignment.R
import com.samaritan.assignment.databinding.ActivityCapturedPokemonsBinding
import com.samaritan.assignment.databinding.ActivityMainBinding
import com.samaritan.assignment.model.ApiEvent
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.utils.DialogUtil
import com.samaritan.assignment.view.PokemonDetailScreenActivity.Companion.POKEMON
import com.samaritan.assignment.view.adapter.CapturedPokemonsAdapter
import com.samaritan.assignment.view.adapter.PokemonsAdapter
import com.samaritan.assignment.viewmodel.CapturedPokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class CapturedPokemonsActivity : BaseActivity<ActivityCapturedPokemonsBinding>(),
    CapturedPokemonsAdapter.CapturedPokemonClickListener {

    private var loading: Boolean = false

    @Inject
    lateinit var dialogUtils: DialogUtil
    private val viewModel: CapturedPokemonsViewModel by viewModels()

    @Inject
    lateinit var capturedPokemonsAdapter: CapturedPokemonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_captured_pokemons)

        viewModel.getCapturedPokemonsList()

        with(binding!!.recylerView) {
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = capturedPokemonsAdapter
        }

        capturedPokemonsAdapter.setParams(this)

        lifecycleScope.launchWhenStarted {
            viewModel.capturedPokemons.collect { list: ArrayList<PokemonListItem> ->
                capturedPokemonsAdapter.setData(list)
            }
        }
    }


    override fun onPokemonItemClicked(pokemon: PokemonListItem) {
        val intent = Intent(this, PokemonDetailScreenActivity::class.java).apply {
            putExtra(POKEMON, pokemon)
        }
        startActivity(intent)
    }
}