package com.chivata.app1.ImcApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chivata.app1.ImcApp.ImcActivity.Companion.IMC_KEY
import com.chivata.app1.R

class ImcResult : AppCompatActivity() {

    private lateinit var tvResultImc: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)

        val result: Float = (intent.extras?.getFloat(IMC_KEY) ?: -1.0) as Float
        initComponent()
        initUI(result)
        initListener()
    }

    private fun initListener() {
        btnRecalculate.setOnClickListener {
            finish()
        }
    }

    private fun initUI(result: Float) {
        tvResultImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {//Peso bajo
                tvResult.text = getString(R.string.peso_bajo)
                tvDescription.text = getString(R.string.peso_bajo_description)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
            }

            in 18.51..24.99 -> {//Peso normal
                tvResult.text = getString(R.string.peso_normal)
                tvDescription.text = getString(R.string.peso_normal_description)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
            }

            in 25.00..29.99 -> {//Sobrepeso
                tvResult.text = getString(R.string.sobrepeso)
                tvDescription.text = getString(R.string.sobrepeso_description)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_sobrepeso))

            }

            in 30.00..99.00 -> {//Obesidad
                tvResult.text = getString(R.string.obesidad)
                tvDescription.text = getString(R.string.obesidad_description)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
            }

            else -> {//error
                tvResultImc.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponent() {
        tvResultImc = findViewById(R.id.tvResultImc)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}