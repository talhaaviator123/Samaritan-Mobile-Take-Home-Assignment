package com.samaritan.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samaritan.assignment.MainApplication
import com.samaritan.assignment.R
import com.samaritan.assignment.databinding.PokemonListItemBinding
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import com.samaritan.assignment.utils.getString
import com.samaritan.assignment.viewmodel.PokemonHomepageViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsAdapter @Inject constructor() :
    RecyclerView.Adapter<PokemonsAdapter.ProductViewHolder>() {

    private var pokemonsList: ArrayList<PokemonListItem> = ArrayList()
    private lateinit var mListener: PokemonClickListener
    lateinit var viewModel: PokemonHomepageViewModel
    lateinit var appController: MainApplication

    fun setParams(
        mListener: PokemonClickListener,
        viewModel: PokemonHomepageViewModel,
        appController: MainApplication
    ) {
        this.mListener = mListener
        this.viewModel = viewModel
        this.appController = appController
    }

    fun setData(data: ArrayList<PokemonListItem>) {
        this.pokemonsList = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(pokemonsList[position], mListener, viewModel,appController)

    override fun getItemCount(): Int = pokemonsList.size

    class ProductViewHolder(val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            pokemonListItem: PokemonListItem,
            listener: PokemonClickListener,
            viewModel: PokemonHomepageViewModel,
            appController: MainApplication
        ) {

            if (pokemonListItem.pokemonDetail == null) {
                viewModel.getPokemonDetails(pokemonListItem.pokemonId!!,
                    object : PokemonDetailCallback {
                        override fun onPokemonDetailFetchSuccess(pokemonDetail: PokemonDetail) {
                            val backgroundColor:Int?= appController.hashMap.get(pokemonDetail.types[0].type?.name)
                            backgroundColor?.let {
                                pokemonListItem.backgroundColor=backgroundColor
                                binding.image.setBackgroundColor(backgroundColor)

                            }
                            pokemonListItem.pokemonDetail = pokemonDetail
                            pokemonListItem.typesString=(pokemonDetail.types).getString()
                            binding.pokemon = pokemonListItem
                            binding.executePendingBindings()
                        }
                    })
            }

            binding.pokemon = pokemonListItem
            binding.pokemonItemClick = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: PokemonListItemBinding = DataBindingUtil
                    .inflate(
                        layoutInflater, R.layout.pokemon_list_item,
                        parent, false
                    )
                return ProductViewHolder(binding)
            }
        }
    }

    interface PokemonClickListener {
        fun onPokemonItemClicked(pokemon: PokemonListItem)
    }

    interface PokemonDetailCallback {
        fun onPokemonDetailFetchSuccess(pokemonDetail: PokemonDetail)
    }


}


