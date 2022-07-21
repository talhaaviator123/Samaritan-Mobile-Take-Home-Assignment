package com.samaritan.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samaritan.assignment.R
import com.samaritan.assignment.databinding.CapturedPokemonListItemBinding
import com.samaritan.assignment.databinding.PokemonListItemBinding
import com.samaritan.assignment.model.pokemon.PokemonListItem
import com.samaritan.assignment.model.pokemon.detail.PokemonDetail
import com.samaritan.assignment.utils.getString
import com.samaritan.assignment.view.CapturedPokemonsActivity
import com.samaritan.assignment.viewmodel.CapturedPokemonsViewModel
import com.samaritan.assignment.viewmodel.PokemonHomepageViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CapturedPokemonsAdapter @Inject constructor() :
    RecyclerView.Adapter<CapturedPokemonsAdapter.ProductViewHolder>() {

    private var pokemonsList: ArrayList<PokemonListItem> = ArrayList()
    private lateinit var mListener: CapturedPokemonClickListener
    lateinit var viewModel: PokemonHomepageViewModel

    fun setParams(mListener: CapturedPokemonClickListener) {
        this.mListener = mListener
    }

    fun setData(data: ArrayList<PokemonListItem>) {
        this.pokemonsList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(pokemonsList[position], mListener)

    override fun getItemCount(): Int = pokemonsList.size

    class ProductViewHolder(val binding: CapturedPokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            pokemonListItem: PokemonListItem,
            listener: CapturedPokemonClickListener,
        ) {
            binding.pokemon = pokemonListItem
            binding.pokemonItemClick = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: CapturedPokemonListItemBinding = DataBindingUtil
                    .inflate(
                        layoutInflater, R.layout.captured_pokemon_list_item,
                        parent, false
                    )
                return ProductViewHolder(binding)
            }
        }
    }

    interface CapturedPokemonClickListener {
        fun onPokemonItemClicked(pokemon: PokemonListItem)
    }



}


