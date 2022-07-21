package com.samaritan.assignment.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.samaritan.assignment.R
import com.samaritan.assignment.databinding.ActivityPokemonDetailScreenBinding
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.utils.DialogUtil
import com.samaritan.assignment.viewmodel.PokemonsDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PokemonDetailScreenActivity : BaseActivity<ActivityPokemonDetailScreenBinding>() {
    companion object {
        const val POKEMON = "pokemon"
        const val IS_FROM_HOMEPAGE = "is_from_homepage"
    }

    @Inject
    lateinit var dialogUtils: DialogUtil


    private val viewModel: PokemonsDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_pokemon_detail_screen)

        intent.getBooleanExtra(IS_FROM_HOMEPAGE, false).let {
            if (it) {
                binding?.cardCaptured?.visibility = View.GONE
                binding?.btnCapture?.visibility = View.VISIBLE
            } else {
                binding?.cardCaptured?.visibility = View.VISIBLE
                binding?.btnCapture?.visibility = View.GONE

            }
        }

        intent.getSerializableExtra(POKEMON)?.let {
            viewModel.pokemonListItem = it as PokemonListItem
        }

        setObserverModelData()

        binding!!.observerModel = viewModel.pokemonDetailsObserverModel
        binding!!.pokemon = viewModel.pokemonListItem

        viewModel.pokemonListItem?.backgroundColor?.let {
            binding?.layoutImage?.setBackgroundColor(it)

        }
        binding?.btnCapture?.setOnClickListener {
            CaptureDialog(viewModel).show(supportFragmentManager, "CaptureDialog")
        }
    }

    private fun setObserverModelData() {
        viewModel.pokemonDetailsObserverModel.name =
            "#${viewModel.pokemonListItem?.pokemonDetail?.order} ${viewModel.pokemonListItem?.name}"
        viewModel.pokemonDetailsObserverModel.types = viewModel.pokemonListItem?.typesString!!
        viewModel.pokemonDetailsObserverModel.weight = "${ viewModel.pokemonListItem?.pokemonDetail?.weight!! / 10 } kg"
        viewModel.pokemonDetailsObserverModel.height = "${ viewModel.pokemonListItem?.pokemonDetail?.height!! / 10 } m"

        viewModel.pokemonListItem?.pokemonDetail?.stats?.forEachIndexed { index, stats ->
            when(stats.stat?.name){
                "hp"-> {
                    viewModel.pokemonDetailsObserverModel.hp=stats.baseStat.toString()
                }
                "attack"-> {
                    viewModel.pokemonDetailsObserverModel.attack=stats.baseStat.toString()
                }
                "deffence"-> {
                    viewModel.pokemonDetailsObserverModel.deffence=stats.baseStat.toString()
                }
                "special-attack"-> {
                    viewModel.pokemonDetailsObserverModel.specialAttak=stats.baseStat.toString()
                }
                "special-defense"-> {
                    viewModel.pokemonDetailsObserverModel.specialDeffence=stats.baseStat.toString()
                }
                "speed"-> {
                    viewModel.pokemonDetailsObserverModel.speed=stats.baseStat.toString()
                }
            }
        }

    }

}