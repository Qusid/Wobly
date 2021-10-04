package com.example.drunkapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.setBackgroundTintList
import kotlin.random.Random

class ReactionTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reaction_test)
        var starttime: Long = 0
        var colorbox = findViewById(R.id.colorbutton) as Button
        var textview = findViewById(R.id.reactiontext) as TextView
        var color = "purple"
        val tim = Random.nextLong(5000, 8000)
        //Thread.sleep(tim)
        var handler = Handler()
        handler.postDelayed({
            colorbox.setBackgroundTintList(
                ContextCompat.getColorStateList(
                    this@ReactionTest,
                    R.color.green
                )
            )
            color = "green"
            starttime = System.nanoTime()//Do Something
        }, tim)
        colorbox.setOnClickListener {
            if(color == "green"){
                colorbox.setBackgroundTintList(
                    ContextCompat.getColorStateList(
                        this@ReactionTest,
                        R.color.white
                    )
                )
                val stoptime = System.nanoTime()
                var reaction = stoptime - starttime
                reaction /= 1000000
                colorbox.setText("$reaction ms")
                colorbox.setTextColor(R.color.black)
                color = "white"
                handler.postDelayed({
                    val intent = Intent(this, ScreenTapTest::class.java)
                    startActivity(intent)
                }, 2000)

            }
        }
    }
}

