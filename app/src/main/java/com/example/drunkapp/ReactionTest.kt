package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ReactionTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reaction_test)
    }
    fun GoBackIntent(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}