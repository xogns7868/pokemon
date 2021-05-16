package com.tom.pokemon.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tom.pokemon.R
import com.tom.pokemon.databinding.ItemPokemonBinding
import com.tom.pokemon.domain.entity.PokeMonDetail


class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>(){
    var itemList =  listOf<PokeMonDetail>()
    var itemOnClick: (PokeMonDetail) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<ItemPokemonBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_pokemon,
            parent,
            false
        ).let{
            SearchViewHolder(it)
        }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.itemPokemonBinding.textView.setOnClickListener {
            itemOnClick(itemList[position])
        }

    }

    fun setItems(items: List<PokeMonDetail>){
        this.itemList = items
        notifyDataSetChanged()
    }

}
class SearchViewHolder(val itemPokemonBinding: ItemPokemonBinding):
    RecyclerView.ViewHolder(itemPokemonBinding.root){
    fun bind(item: PokeMonDetail){
        itemPokemonBinding.apply{
            pokemon = item
            executePendingBindings()
        }
    }
}