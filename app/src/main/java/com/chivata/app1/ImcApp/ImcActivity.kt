package com.chivata.app1.ImcApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.chivata.app1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Float = 40f
    private var currentAge: Int = 0
    private var currentHeight: Float = 120f

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWieght: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        initComponent()
        initListener()
        initUI()
    }


    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWieght = findViewById(R.id.btnAddWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListener() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->

            val df = DecimalFormat("#.##")
            val currentHeight = df.format(value).toFloat()
            tvHeight.text = "${currentHeight.toString()} cm"
        }

        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnAddWieght.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnAddAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            calculateImc()
            /**
            val intent = Intent(this, ImcResult::class.java)
            intent.putExtra("VALUE_CALCULATED", calculateImc())
            startActivity(intent) **/
        }
    }

    private fun calculateImc() {
        val imc = currentWeight / (currentHeight * currentHeight)
        Log.i("jhon", "el imc es $imc")

        /**
        val result = when (imc) {
        in 0..19 -> "Bajo peso"
        in 20..25 -> "Peso Normal"
        in 26..40 -> "Obesidad"
        else -> "NOT FOUND"
        }
        return result **/
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectecComponent: Boolean): Int {
        val colorReference = if (isSelectecComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}