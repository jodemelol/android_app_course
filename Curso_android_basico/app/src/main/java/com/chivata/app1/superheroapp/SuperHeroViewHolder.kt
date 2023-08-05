package com.chivata.app1.superheroapp

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chivata.app1.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse, onItemSelected: (String) -> Unit) {
        binding.tvSuperHeroName.text = superHeroItemResponse.superHeroName
        Picasso.get().load(superHeroItemResponse.superHeroImg.url).into(binding.ivSuperHero)
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.superHeroId)
        Log.i("jhon",superHeroItemResponse.superHeroId)
        }
    }

}