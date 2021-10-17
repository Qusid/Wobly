package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.random.Random

class ScreenTapTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_tap_test)
        var redcircle = findViewById(R.id.redbutton) as Button
        var bluecircle = findViewById(R.id.bluebutton) as Button
        var greencircle = findViewById(R.id.greenbutton) as Button
        var yellowcircle = findViewById(R.id.yellowbutton) as Button
        var textview = findViewById(R.id.screentaptext) as TextView
        var correct = 0
        var taps = 0
        var handler = Handler()
        handler.postDelayed({
                var color = Random.nextInt(0, 4)
                when (color) {
                    0 -> {
                        textview.setText("Tap Red")
                    }
                    1 -> {
                        textview.setText("Tap Blue")
                    }
                    2 -> {
                        textview.setText("Tap Green")
                    }
                    3 -> {
                        textview.setText("Tap Yellow")
                    }
                }
                redcircle.setOnClickListener {
                    if (color == 0) {
                        correct++
                        taps++
                        while(color == 0)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Blue")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                            }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
                }
                bluecircle.setOnClickListener {
                    if (color == 1) {
                        correct++
                        taps++
                        while(color == 1)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Blue")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                            }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
                }
                greencircle.setOnClickListener {
                    if (color == 2) {
                        correct++
                        taps++
                        while(color == 2)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Blue")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                            }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
                }
                yellowcircle.setOnClickListener {
                    if (color == 3) {
                        correct++
                        taps++
                        while(color == 3)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Blue")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                        }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
            }
        }, 5000)
        handler.postDelayed({
            val accuracy = (correct * 100/taps)
            textview.setText("Correct Taps: $correct   Accuracry: $accuracy%")
            textview.setTextColor(resources.getColor(R.color.black))
            yellowcircle.setOnClickListener(null)
            redcircle.setOnClickListener(null)
            greencircle.setOnClickListener(null)
            bluecircle.setOnClickListener(null)
                            }, 15000)
        handler.postDelayed({
            val intent = Intent(this, CodeSubstitutionTest::class.java)
            startActivity(intent)
        }, 16000)
    }
}