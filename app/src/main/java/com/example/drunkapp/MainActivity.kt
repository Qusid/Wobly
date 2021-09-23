package com.example.drunkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onStart() {
        super.onStart()
        setupBottomNavigationMenu()
    }
    private fun setupBottomNavigationMenu() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragments)

        bottomNavigationView.setupWithNavController(navController)
    }


    fun ReactionTestIntent(view: View?) {
        val intent = Intent(this, ReactionTest::class.java)
        startActivity(intent)
    }

}