package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import android.content.SharedPreferences
import android.view.View


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
        val substitutionaccuracy = b?.getInt("substitutionaccuracy").toString()
        val substitutioncorrect = b?.getInt("substitutioncorrect").toString()
        val visualincorrect = b?.getInt("VisualIncorrect").toString()

        reactionresult.setText("$reaction ms")
        screentapresult.setText("Correct: $screentapcorrect Accuracy: $screentapaccuracy %")
        codesubresult.setText("Correct: $substitutioncorrect")


        if(b?.getString("TestType") == "Impair"){
            val data = applicationContext.getSharedPreferences("LastTest", 0)
            val editor = data.edit()
            editor.putString("userreaction", reaction)
            editor.putString("userscreentapaccuracy", screentapaccuracy)
            editor.putString("userscreentapcorrect", screentapcorrect)
            editor.putString("usersubstitutionaccuracy", substitutionaccuracy)
            editor.putString("usersubstitutioncorrect", substitutioncorrect)
            editor.putString("uservisualcorrect", visualincorrect)
            editor.apply()

        }
        else if(b?.getString("TestType") == "setup"){
            val data = applicationContext.getSharedPreferences("UserBaseVal", 0)
            val editor = data.edit()
            editor.putString("userreaction", reaction)
            editor.putString("userscreentapaccuracy", screentapaccuracy)
            editor.putString("userscreentapcorrect", screentapcorrect)
            editor.putString("usersubstitutionaccuracy", substitutionaccuracy)
            editor.putString("usersubstitutioncorrect", substitutioncorrect)
            editor.putString("uservisualcorrect", visualincorrect)
            editor.apply()

        }





    }
    fun HomeIntent(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}