package com.example.drunkapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.setBackgroundTintList
import kotlin.random.Random

class ReactionTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reaction_test)
        var starttime: Long = 0
        var colorbox = findViewById(R.id.colorbutton) as Button
        colorbox.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val tim = Random.nextLong(1500, 3500)
                        Thread.sleep(tim)
                        colorbox.setBackgroundTintList(
                            ContextCompat.getColorStateList(
                                this@ReactionTest,
                                R.color.green
                            )
                        )
                        starttime = System.nanoTime()//Do Something
                    }
                    MotionEvent.ACTION_UP -> {
                        val stoptime = System.nanoTime()
                        var reaction = stoptime - starttime
                        reaction /= 1000000
                        colorbox.setText("$reaction ms")
                    }
                }
                return true
            }
        })
    }
    }

