package com.chivata.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.chivata.app1.ImcApp.ImcActivity
import com.chivata.app1.TodoApp.TodoActivity
import com.chivata.app1.firstApp.FirstAppActivity
import com.chivata.app1.settings.SettingsActivity
import com.chivata.app1.superheroapp.SuperHeroListActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<AppCompatButton>(R.id.btnSaludApp)
        btnSaludApp.setOnClickListener { navigateToSaludApp() }

        val btnImcApp = findViewById<AppCompatButton>(R.id.btnImcApp)
        btnImcApp.setOnClickListener { navigateToImcApp() }

        val btnTODO = findViewById<AppCompatButton>(R.id.btnTODO)
        btnTODO.setOnClickListener { navigateToTodoApp() }

        val btnSuperHero = findViewById<AppCompatButton>(R.id.btnSuperHero)
        btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }

        val btnSettings = findViewById<AppCompatButton>(R.id.btnSettings)
        btnSettings.setOnClickListener { navigateToSettings() }
    }

    private fun navigateToSettings() {
        val intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }


    private fun navigateToTodoApp() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludApp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToImcApp(){
        val intent = Intent(this, ImcActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHeroApp(){
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

}