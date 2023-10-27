package com.jroslar.heroapp.ui.searchhero.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jroslar.heroapp.R
import com.jroslar.heroapp.domain.model.HeroModel

class SearchHeroAdapter(private var heroList: List<HeroModel> = emptyList(),
    private val onItemSelect:(HeroModel) -> Unit) :
    RecyclerView.Adapter<SearchHeroViewHolder>() {

    fun updateList(list: List<HeroModel>) {
        heroList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHeroViewHolder {
        return  SearchHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        )
    }

    override fun getItemCount() = heroList.size

    override fun onBindViewHolder(holder: SearchHeroViewHolder, position: Int) {
        holder.bind(heroList[position], onItemSelect)
    }
}