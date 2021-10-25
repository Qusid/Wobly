package com.example.drunkapp


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView

import kotlin.random.Random

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.toColorInt
import java.text.DecimalFormat
import java.text.NumberFormat


class VisualMemory : AppCompatActivity() {


    var  tim = List(3) { Random.nextInt(0, 6) }
    var  newarray = List(3) { Random.nextInt(0, 5) }.toMutableList()
    private val TAG = "MyActivity"
    var flag = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_memory)
        for(i in 0..9) {
            if (tim.size != tim.distinct().count()) {
                tim = List(3) { Random.nextInt(0, 6) }
            }
            else{
                break
            }
        }


        val timer = findViewById<View>(R.id.Timer) as TextView
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        object : CountDownTimer(20000, 1000) {
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

        var imgone = 2000.toLong()
        var imgtwo = 3000.toLong()
        var imgthe = 4000.toLong()
        var imgsel = 6000.toLong()

        var handler = Handler()
        val img1 = findViewById<View>(R.id.FirstImage) as ImageView
        val img2 = findViewById<View>(R.id.Secondimage) as ImageView
        val img3 = findViewById<View>(R.id.ThirdImage) as ImageView
        val text1 = findViewById<View>(R.id.textView5) as TextView
        val text2 = findViewById<View>(R.id.textView6) as TextView
        val imgsel1 = findViewById<View>(R.id.sel1) as ImageView
        val imgsel2 = findViewById<View>(R.id.sel2) as ImageView
        val imgsel3 = findViewById<View>(R.id.sel3) as ImageView
        val imgsel4 = findViewById<View>(R.id.sel4) as ImageView
        val imgsel5 = findViewById<View>(R.id.sel5) as ImageView
        val imgsel6 = findViewById<View>(R.id.sel6) as ImageView



        var photos = intArrayOf(R.drawable.cows, R.drawable.dog, R.drawable.lion, R.drawable.roo, R.drawable.fish, R.drawable.tur, R.drawable.blank)
        handler.postDelayed({

            img1.isVisible = true
            img1.setImageResource(photos[tim[0]])

        }, imgone)
        handler.postDelayed({

            img2.isVisible = true
            img2.setImageResource(photos[tim[1]])
        }, imgtwo)
        handler.postDelayed({

            img3.isVisible = true
            img3.setImageResource(photos[tim[2]])
        }, imgthe)

        handler.postDelayed({
            img1.isVisible = false
            img2.isVisible = false
            img3.isVisible = false
            text1.isVisible = false
            text2.isVisible = true

            imgsel1.isVisible = true
            imgsel2.isVisible = true
            imgsel3.isVisible = true
            imgsel4.isVisible = true
            imgsel5.isVisible = true
            imgsel6.isVisible = true


        }, imgsel)
        handler.postDelayed({
            if(flag == false) {
                val intent = Intent(this, CodeSubstitutionTest::class.java)
                val b = getIntent().extras
                if (b != null) {
                    intent.putExtras(b)
                }
                startActivity(intent)
            }
        }, 16000)


    }



    fun SetupSel(view: View?) {

        val img1 = findViewById<View>(R.id.FirstImage) as ImageView
        val img2 = findViewById<View>(R.id.Secondimage) as ImageView
        val img3 = findViewById<View>(R.id.ThirdImage) as ImageView
        val textView: TextView = findViewById<TextView>(R.id.results)
        val intent = Intent(this, CodeSubstitutionTest::class.java)
        val b = getIntent().extras
        if (b != null) {
            intent.putExtras(b)
        }

        when (view?.getId()) {


            R.id.sel1 -> {

                if(!(img1.isVisible)){
                    img1.setImageResource(R.drawable.cows)
                    img1.isVisible = true
                    newarray[0] = 0
                }
                else if(!(img2.isVisible)){
                    img2.setImageResource(R.drawable.cows)
                    img2.isVisible = true
                    newarray[1] = 0
                }
                else {
                    img3.setImageResource(R.drawable.cows)
                    img3.isVisible = true
                    newarray[2] = 0
                    if(tim ==newarray){
                        textView.text = getString(R.string.results_vmt)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.green))
                        flag = true
                        startActivity(intent)
                    }
                    else{
                        textView.text = getString(R.string.results_vmt0)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.red))
                        img1.isVisible = false
                        img2.isVisible = false
                        img3.isVisible = false
                        textView.isVisible = false
                    }
                    //Log.i(TAG, "cow $newarray")
                }


            }

            R.id.sel2 -> {

                if(!(img1.isVisible)){
                    img1.setImageResource(R.drawable.dog)
                    img1.isVisible = true
                    newarray[0] = 1
                }
                else if(!(img2.isVisible)){
                    img2.setImageResource(R.drawable.dog)
                    img2.isVisible = true
                    newarray[1] = 1
                }
                else {
                    img3.setImageResource(R.drawable.dog)
                    img3.isVisible = true
                    newarray[2] = 1
                    if(tim ==newarray){
                        textView.text = getString(R.string.results_vmt)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.green))
                        flag = true
                        startActivity(intent)
                    }
                    else{
                        textView.text = getString(R.string.results_vmt0)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.red))
                        img1.isVisible = false
                        img2.isVisible = false
                        img3.isVisible = false
                        textView.isVisible = false
                    }
                }
                //Log.i(TAG, "dog $newarray")
            }
            R.id.sel3 -> {

                if(!(img1.isVisible)){
                    img1.setImageResource(R.drawable.lion)
                    img1.isVisible = true
                    newarray[0] = 2
                }
                else if(!(img2.isVisible)){
                    img2.setImageResource(R.drawable.lion)
                    img2.isVisible = true
                    newarray[1] = 2
                }
                else {
                    img3.setImageResource(R.drawable.lion)
                    img3.isVisible = true
                    newarray[2] = 2
                    if(tim ==newarray){
                        textView.text = getString(R.string.results_vmt)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.green))
                        flag = true
                        startActivity(intent)
                    }
                    else{
                        textView.text = getString(R.string.results_vmt0)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.red))
                        img1.isVisible = false
                        img2.isVisible = false
                        img3.isVisible = false
                        textView.isVisible = false
                    }
                }

               // Log.i(TAG, "lion $newarray")
            }
            R.id.sel4 -> {
                if(!(img1.isVisible)){
                    img1.setImageResource(R.drawable.roo)
                    img1.isVisible = true
                    newarray[0] = 3
                }
                else if(!(img2.isVisible)){
                    img2.setImageResource(R.drawable.roo)
                    img2.isVisible = true
                    newarray[1] = 3
                }
                else {
                    img3.setImageResource(R.drawable.roo)
                    img3.isVisible = true
                    newarray[2] = 3
                    if(tim ==newarray){
                        textView.text = getString(R.string.results_vmt)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.green))
                        flag = true
                        startActivity(intent)
                    }
                    else{
                        textView.text = getString(R.string.results_vmt0)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.red))
                        img1.isVisible = false
                        img2.isVisible = false
                        img3.isVisible = false
                        textView.isVisible = false
                    }
                }
               // Log.i(TAG, "roo $newarray")
            }
            R.id.sel5 -> {
                if(!(img1.isVisible)){
                    img1.setImageResource(R.drawable.fish)
                    img1.isVisible = true
                    newarray[0] = 4
                }
                else if(!(img2.isVisible)){
                    img2.setImageResource(R.drawable.fish)
                    img2.isVisible = true
                    newarray[1] = 4
                }
                else {
                    img3.setImageResource(R.drawable.fish)
                    img3.isVisible = true
                    newarray[2] = 4
                    if(tim ==newarray){
                        textView.text = getString(R.string.results_vmt)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.green))
                        flag = true
                        startActivity(intent)
                    }
                    else{
                        textView.text = getString(R.string.results_vmt0)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.red))
                        img1.isVisible = false
                        img2.isVisible = false
                        img3.isVisible = false
                        textView.isVisible = false
                    }
                }

                //Log.i(TAG, "fish $newarray")
            }
            R.id.sel6 -> {
                if(!(img1.isVisible)){
                    img1.setImageResource(R.drawable.tur)
                    img1.isVisible = true
                    newarray[0] = 5
                }
                else if(!(img2.isVisible)){
                    img2.setImageResource(R.drawable.tur)
                    img2.isVisible = true
                    newarray[1] = 5
                }
                else {
                    img3.setImageResource(R.drawable.tur)
                    img3.isVisible = true
                    newarray[2] = 5
                    if(tim ==newarray){
                        textView.text = getString(R.string.results_vmt)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.green))
                        flag = true
                        startActivity(intent)
                    }
                    else{
                        textView.text = getString(R.string.results_vmt0)
                        textView.isVisible = true
                        textView.setTextColor(resources.getColor(R.color.red))
                        img1.isVisible = false
                        img2.isVisible = false
                        img3.isVisible = false
                        textView.isVisible = false

                    }
                }
                //Log.i(TAG, "tur $newarray")
            }



        }
    }






}

