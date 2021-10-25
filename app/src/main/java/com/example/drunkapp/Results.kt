package com.example.drunkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val reactionresult = findViewById(R.id.reactionresult) as TextView
        val screentapresult = findViewById(R.id.screentapresult) as TextView
        val codesubresult = findViewById(R.id.codesubresult) as TextView
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val b = getIntent().extras
        val reaction = b?.getLong("reaction").toString()
        val screentapaccuracy = b?.getInt("screentapaccuracy").toString()
        val screentapcorrect = b?.getInt("screentapcorrect").toString()
        val substitutionaccuracy = b?.getString("substitutionaccuracy")
        val substitutioncorrect = b?.getInt("substitutioncorrect").toString()
        reactionresult.setText("$reaction ms")
        screentapresult.setText("Correct: $screentapcorrect Accuracy: $screentapaccuracy %")
        codesubresult.setText("Correct: $substitutioncorrect")
    }
}