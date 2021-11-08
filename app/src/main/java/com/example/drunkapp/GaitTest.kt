package com.example.drunkapp

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import java.io.File
import java.io.FileWriter
import java.io.IOException
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.properties.Delegates


class GaitTest : AppCompatActivity(), SensorEventListener {
    private lateinit var gyroscope1 : TextView
    private lateinit var gyroscope2 : TextView
    private lateinit var gyroscope3 : TextView
    private lateinit var accelerometer1 : TextView
    private lateinit var accelerometer2 : TextView
    private lateinit var accelerometer3 : TextView
    private lateinit var sensorManager : SensorManager
    private var stoptime = 0L
    private var starttime = 0L
    lateinit var fileOut1: File
    lateinit var fileOut2: File
    var handler = Handler()
    var h = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gait_test)
        val b = getIntent().extras
        val testtype = b?.getString("TestType").toString()
        gyroscope1 = findViewById(R.id.gyroscope1) as TextView
        gyroscope2 = findViewById(R.id.gyroscope2) as TextView
        gyroscope3 = findViewById(R.id.gyroscope3) as TextView
        accelerometer1 = findViewById(R.id.accelerometer1) as TextView
        accelerometer2 = findViewById(R.id.accelerometer2) as TextView
        accelerometer3 = findViewById(R.id.accelerometer3) as TextView
        gyroscope1.setText("Please start walking as soon as this text changes (Hold your phone in front of you in your right hand as you normally would)")
        h.postDelayed({
            sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_FASTEST)
            }
            sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE).also {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_FASTEST)
            }
            gyroscope1.setText("")
            starttime = System.nanoTime()
        }, 5000)

        val headers1 = "Time,Accel X,Accel Y,Accel Z"
        val filename1 = "setup_motion1.csv"
        val headers2 = "Time,Gyro X,Gyro Y,Gyro Z"
        val filename2 = "setup_motion2.csv"
        val filename3 = "test_motion1.csv"
        val filename4 = "test_motion2.csv"
        val path = getExternalFilesDir(null)   //get file directory for this package
//(Android/data/.../files | ... is your app package)

        //create fileOut object
        if(testtype == "setup") {
            fileOut1 = File(path, filename1)
            fileOut2 = File(path, filename2)
        }
        else if(testtype == "Impair"){
            fileOut1 = File(path, filename3)
            fileOut2 = File(path, filename4)
        }
        fileOut1.delete()
        fileOut2.delete()
        fileOut1.createNewFile()
        fileOut1.appendText(headers1)
        fileOut1.appendText("\n")
        fileOut2.createNewFile()
        fileOut2.appendText(headers2)
        fileOut2.appendText("\n")
        var accelx: Float = 0F
        var accely: Float = 0F
        var accelz: Float = 0F
        var gyrox: Float = 0F
        var gyroy: Float = 0F
        var gyroz: Float = 0F
        var rows: List<List<String>>
        var rows2: List<List<String>>
        handler.postDelayed ({
            sensorManager.unregisterListener(this)
            if(testtype == "setup"){
                gyroscope1.setText("Your setup is complete. You may go back.")
                gyroscope2.setText("")
                gyroscope3.setText("")
                accelerometer1.setText("")
                accelerometer2.setText("")
                accelerometer3.setText("")
            }
            else if(testtype == "Impair") {
                if (File(path, filename1).exists() && File(path, filename2).exists()) {
                var count1 = 0
                var counter = 0
                var count2 = 0
                var counter2 = 0
                var summ1 = 0F
                var summ2 = 0F
                var summ3 = 0F
                rows = csvReader().readAll(fileOut1)
                    csvReader().open("${path}/${filename1}") {
                        readAllAsSequence().forEach { row: List<String> ->
                            if (count1 != 0  && count1 < rows.size) {
                                if (row[0] == rows[count1][0]) {
                                    accelx += Math.abs(rows[count1][1].toFloat()) - Math.abs(row[1].toFloat())
                                    summ1 += Math.abs(row[1].toFloat())
                                    accely += Math.abs(rows[count1][2].toFloat()) - Math.abs(row[2].toFloat())
                                    summ2 += Math.abs(row[2].toFloat())
                                    accelz += Math.abs(rows[count1][3].toFloat()) - Math.abs(row[3].toFloat())
                                    summ3 +=  Math.abs(row[3].toFloat())
                                    counter++
                                }
                            }
                            count1++
                        }
                    }
                    var summx = 0F
                    var summy = 0F
                    var summz = 0F
                    rows2 = csvReader().readAll(fileOut2)
                csvReader().open("${path}/${filename2}") {
                    readAllAsSequence().forEach { row: List<String> ->
                        if (count2 != 0  && count2 < rows2.size) {
                            if (row[0] == rows[count2][0]) {
                                gyrox += (Math.abs(rows2[count2][1].toFloat())) - Math.abs(row[1].toFloat())
                                summx += Math.abs(row[1].toFloat())
                                gyroy += (Math.abs(rows2[count2][2].toFloat())) - Math.abs(row[2].toFloat())
                                summy+= Math.abs(row[2].toFloat())
                                gyroz += Math.abs((rows2[count2][3].toFloat())) - Math.abs(row[3].toFloat())
                                summz += Math.abs(row[3].toFloat())
                                counter2++
                            }
                        }
                        count2++
                    }
                }
                val averagex = ((accelx/summ1))
                    println(averagex)
                val averagey = ((accely/summ2))
                    println(averagey)
                val averagez = ((accelz/summ3))
                    println(averagez)
                var avgaccel = averagex + averagey + averagez
                    avgaccel /= 3
                    avgaccel *= 100
                val avgx = ((gyrox/summx))
                    println(avgx)
                val avgy = ((gyroy/summy))
                    println(avgy)
                val avgz = ((gyroz/summz))
                    println(avgz)
                var avggyro = avgx + avgy + avgz
                    avggyro /= 3
                    avggyro *= 100
                gyroscope1.setText("Difference Accel = ${Math.abs(avgaccel)}%")
                gyroscope2.setText("Difference Gyro = ${Math.abs(avggyro)}%")
                    gyroscope3.setText("")
                    accelerometer1.setText("")
                    accelerometer2.setText("")
                    accelerometer3.setText("")
            }
               else{
                   gyroscope1.setText("")
                    gyroscope2.setText("")
                   gyroscope3.setText("Setup not completed. Please finish the setup test in settings first.")
                    accelerometer1.setText("")
                    accelerometer2.setText("")
                    accelerometer3.setText("")
                }
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
               // finish()
            }
            /*
            //var urr1: Uri = Uri.fromFile(fileOut2)
            //var urr: Uri = Uri.fromFile(fileOut1)
            val urr = FileProvider.getUriForFile(
                this,
                "com.example.drunkapp.provider",  //(use your app signature + ".provider" )
                fileOut1
            )
            val urr1 = FileProvider.getUriForFile(
                this,
                "com.example.drunkapp.provider",  //(use your app signature + ".provider" )
                fileOut2
            )
            var uris = ArrayList<Uri>()
            uris.add(urr)
            uris.add(urr1)
            if(uris == null)
                println("still null")
            val email: String = "mailto:@lakeheadu.ca"
            val sendIntent = Intent(Intent.ACTION_SEND_MULTIPLE)
            //data = Uri.parse("mailto:")
            sendIntent.type = "plain/text"
            sendIntent.putExtra(Intent.EXTRA_EMAIL, email)
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Data CSV")
            sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
            startActivity(Intent.createChooser(sendIntent, "SHARE"))
            finish()

             */
        }, 10000)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val xax = p0.values[0]
            val yax = p0.values[1]
            val zax = p0.values[2]
            stoptime = System.nanoTime()
            val currtime = (stoptime - starttime)
            fileOut1.appendText((currtime/1000000000).toString())
            fileOut1.appendText(",")
            fileOut1.appendText(xax.toString())
            fileOut1.appendText(",")
            fileOut1.appendText(yax.toString())
            fileOut1.appendText(",")
            fileOut1.appendText(zax.toString())
            fileOut1.appendText("\n")
                accelerometer1.setText("Accelerometer X: ${xax.toInt()}")
                accelerometer2.setText("Accelerometer Y: ${yax.toInt()}")
                accelerometer3.setText("Accelerometer Z: ${zax.toInt()}")

        }
        else if(p0?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val xax = p0.values[0]
            val yax = p0.values[1]
            val zax = p0.values[2]
            stoptime = System.nanoTime()
            val currtime = (stoptime - starttime)
            fileOut2.appendText((currtime/1000000000).toString())
            fileOut2.appendText(",")
            fileOut2.appendText(xax.toString())
            fileOut2.appendText(",")
            fileOut2.appendText(yax.toString())
            fileOut2.appendText(",")
            fileOut2.appendText(zax.toString())
            fileOut2.appendText("\n")
                gyroscope1.setText("Gyroscope X: ${xax.toInt()}")
                gyroscope2.setText("Gyroscope Y: ${yax.toInt()}")
                gyroscope3.setText("Gyroscope Z: ${zax.toInt()}")

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onPause() {
        handler.removeCallbacksAndMessages(null);
        h.removeCallbacksAndMessages(null)
        super.onPause()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }

    override fun onDestroy() {
        //sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}