package com.chivata.app1.firstApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.chivata.app1.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        textViewResult.text = "hola $name"
    }
}