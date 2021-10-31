package com.example.drunkapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.setBackgroundTintList
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.random.Random

class ReactionTest : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reaction_test)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val timer = findViewById<View>(R.id.TimerReaction) as TextView

        object : CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                timer.setText(
                    f.format(sec)

                )

            }

            // When the task is over it will print 00:00:00 there
            override fun onFinish() {
                timer.setText("00:00:00")
            }
        }.start()

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
                    val b = getIntent().extras
                    if (b != null) {
                        intent.putExtras(b)
                    }
                    intent.putExtra("reaction", reaction)
                    startActivity(intent)
                    finish()
                }, 2000)

            }
        }
    }
}

