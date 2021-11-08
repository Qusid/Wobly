package com.example.drunkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_history)

        var His = findViewById(R.id.Historytext) as TextView



        val data = applicationContext.getSharedPreferences("LastTest", 0)
        val reaction = data.getString("userreaction", "NULL")
        val userscreentapaccuracy = data.getString("userscreentapaccuracy", "NULL")
        val userscreentapcorrect = data.getString("userscreentapcorrect", "NULL")
        val usersubstitutioncorrect = data.getString("usersubstitutioncorrect", "NULL")
        val uservisualcorrect = data.getString("uservisualcorrect", "NULL")

        His.setText("User reaction test : $reaction \n" +
                "userscreentapaccuracy $userscreentapaccuracy \n" +
                "userscreentapcorrect : $userscreentapcorrect \n" +
                "usersubstitutioncorrect: $usersubstitutioncorrect\n" +
                "Visual Memory accuracy: $uservisualcorrect")
    }
}