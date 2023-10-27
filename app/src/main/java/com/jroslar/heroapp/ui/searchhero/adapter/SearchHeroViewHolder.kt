package com.jroslar.heroapp.ui.searchhero.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jroslar.heroapp.databinding.ItemHeroBinding
import com.jroslar.heroapp.domain.model.HeroModel

class SearchHeroViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemHeroBinding.bind(view)
    fun bind(heroModel: HeroModel, onItemSelect: (HeroModel) -> Unit) {
        binding.tvListHeroName.text = heroModel.name
        binding.tvListHeroPublisher.text = heroModel.biography.publisher

        Glide
            .with(view)
            .load(heroModel.image.url)
            .into(binding.ivListHeroAvatar)

        binding.root.setOnClickListener { onItemSelect(heroModel) }
    }
}