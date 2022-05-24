package com.example.sensorlist

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var listSensor: TextView
    private val sensors = arrayOf(
        arrayOf(2, 5, 6, 12, 13, 14),
        arrayOf(21, 31, 34),
        arrayOf(1, 4, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 29, 30, 35, 36)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById<Spinner>(R.id.spinner)
        listSensor = findViewById(R.id.list_sensor)

        ArrayAdapter.createFromResource(
            this,
            R.array.type_sensors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = SpinnerActivity(this::showList)
    }

    private fun showList(position: Int) {
        val sm = getSystemService(SENSOR_SERVICE) as SensorManager
        var text = ""
        if (position < 1) {
            sm.getSensorList(Sensor.TYPE_ALL).forEach {
                text += "${it.name}\n"
            }
        } else if (position > sensors.size) {
            sm.getSensorList(Sensor.TYPE_ALL).forEach {
                var found = false
                for (type in sensors) {
                    if (found) {
                        break
                    }
                    if (it.type in type) {
                        found = true
                    }
                }
                if (!found) {
                    text += "${it.name}\n"
                }
            }
        } else {
            sm.getSensorList(Sensor.TYPE_ALL).forEach {
                if (it.type in sensors[position - 1]) {
                    text += "${it.name}\n"
                }
            }
        }
        text = text.trim()
        listSensor.text = text
    }
}