package com.jroslar.heroapp.ui.searchhero.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jroslar.heroapp.databinding.ItemHeroBinding
import com.jroslar.heroapp.domain.model.HeroModel

class SearchHeroViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemHeroBinding.bind(view)
    fun bind(heroModel: HeroModel) {
        binding.tvListHeroName.text = heroModel.name
        binding.tvListHeroPublisher.text = heroModel.biography.publisher
    }
}