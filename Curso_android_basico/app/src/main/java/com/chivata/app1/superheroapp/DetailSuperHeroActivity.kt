package com.chivata.app1.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import com.chivata.app1.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getHeroInformation(id)
    }

    private fun getHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)
            if (superHeroDetail.body() != null) {
                runOnUiThread { createUI(superHeroDetail.body()!!) }
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.superHeroImg.url).into(binding.ivSuperHero)
        binding.tvNameDetail.text = superhero.name
        binding.tvFullNameDetail.text = superhero.biography.fullName
        binding.tvNameEditorialDetail.text = superhero.biography.publisher

        prepareStats(superhero.powerstats)
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewIntelligence, powerstats.intelligence.toFloat())
        updateHeight(binding.viewStrength, powerstats.strength.toFloat())
        updateHeight(binding.viewSpeed, powerstats.speed.toFloat())
        updateHeight(binding.viewDurability, powerstats.durability.toFloat())
        updateHeight(binding.viewPower, powerstats.power.toFloat())
        updateHeight(binding.viewCombat, powerstats.combat.toFloat())
    }

    private fun updateHeight(view: View, stat: Float){
        val params = view.layoutParams as ViewGroup.LayoutParams
        val heightInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, stat, resources.displayMetrics)
        params.height = heightInPx.toInt()
        view.layoutParams = params
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}