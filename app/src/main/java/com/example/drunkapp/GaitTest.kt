package com.example.drunkapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GaitTest : AppCompatActivity(), SensorEventListener {
    private lateinit var gyroscope1 : TextView
    private lateinit var gyroscope2 : TextView
    private lateinit var gyroscope3 : TextView
    private lateinit var accelerometer1 : TextView
    private lateinit var accelerometer2 : TextView
    private lateinit var accelerometer3 : TextView
    private lateinit var sensorManager : SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gait_test)
        gyroscope1 = findViewById(R.id.gyroscope1) as TextView
        gyroscope2 = findViewById(R.id.gyroscope2) as TextView
        gyroscope3 = findViewById(R.id.gyroscope3) as TextView
        accelerometer1 = findViewById(R.id.accelerometer1) as TextView
        accelerometer2 = findViewById(R.id.accelerometer2) as TextView
        accelerometer3 = findViewById(R.id.accelerometer3) as TextView
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST)
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE).also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val xax = p0.values[0]
            val yax = p0.values[1]
            val zax = p0.values[2]
            accelerometer1.setText("Accelerometer X: ${xax.toInt()}")
            accelerometer2.setText("Accelerometer Y: ${yax.toInt()}")
            accelerometer3.setText("Accelerometer Z: ${zax.toInt()}")
        }
        else if(p0?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val xax = p0.values[0]
            val yax = p0.values[1]
            val zax = p0.values[2]
            gyroscope1.setText("Gyroscope X: ${xax.toInt()}")
            gyroscope2.setText("Gyroscope Y: ${yax.toInt()}")
            gyroscope3.setText("Gyroscope Z: ${zax.toInt()}")
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}