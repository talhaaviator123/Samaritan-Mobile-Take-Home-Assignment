package com.samaritan.assignment.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samaritan.assignment.R
import com.samaritan.assignment.databinding.ActivityMainBinding
import com.samaritan.assignment.model.ApiEvent
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.utils.DialogUtil
import com.samaritan.assignment.view.PokemonDetailScreenActivity.Companion.IS_FROM_HOMEPAGE
import com.samaritan.assignment.view.PokemonDetailScreenActivity.Companion.POKEMON
import com.samaritan.assignment.view.adapter.PokemonsAdapter
import com.samaritan.assignment.viewmodel.PokemonHomepageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), PokemonsAdapter.PokemonClickListener {

    private var loading: Boolean = false

    @Inject
    lateinit var dialogUtils: DialogUtil
    private val viewModel: PokemonHomepageViewModel by viewModels()

    @Inject
    lateinit var pokemonsAdapter: PokemonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)

        viewModel.getPokemonList()



        with(binding!!.recylerView) {
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = pokemonsAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = linearLayoutManager.itemCount
                    val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                    if (!loading
                        && totalItemCount <= (lastVisibleItem + 5)
                    ) {
                        loading = true
                        viewModel.getPokemonList()
                    }

                }
            })
        }

        pokemonsAdapter.setParams(this, viewModel,appController!!)

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { value: ApiEvent ->
                when (value) {
                    is ApiEvent.Success -> {
                        dialogUtils.hideDialog()
                        loading = false
                        pokemonsAdapter.setData(viewModel.pokemonsList)
                        binding!!.recylerView.visibility = View.VISIBLE
                    }
                    is ApiEvent.Failure -> {
                        loading = false
                        dialogUtils.hideDialog()
                        binding!!.recylerView.visibility = View.GONE
                    }
                    is ApiEvent.Loading -> {
                        if (!loading) {
                            dialogUtils.showDialog()
                        }
                    }
                    else -> Unit
                }
            }
        }

        binding?.fab?.setImageDrawable(getDrawable(R.drawable.captured_icon))
        binding?.fab?.setOnClickListener {
            startActivity(Intent(this,CapturedPokemonsActivity::class.java))
        }

    }


    override fun onPokemonItemClicked(pokemon: PokemonListItem) {
        val intent = Intent(this, PokemonDetailScreenActivity::class.java).apply {
            putExtra(POKEMON, pokemon)
            putExtra(IS_FROM_HOMEPAGE,true)
        }
        startActivity(intent)
    }
}