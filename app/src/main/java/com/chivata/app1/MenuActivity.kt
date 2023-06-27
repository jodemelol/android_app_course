package com.chivata.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.chivata.app1.ImcApp.ImcActivity
import com.chivata.app1.firstApp.FirstAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<AppCompatButton>(R.id.btnSaludApp)
        btnSaludApp.setOnClickListener { navigateToSaludApp() }

        val btnImcApp = findViewById<AppCompatButton>(R.id.btnImcApp)
        btnImcApp.setOnClickListener { navigateToImcApp() }
    }

    private fun navigateToSaludApp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToImcApp(){
        val intent = Intent(this, ImcActivity::class.java)
        startActivity(intent)
    }
}