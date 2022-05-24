package com.example.sensorlist

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val listSensor = findViewById<TextView>(R.id.list_sensor)

        ArrayAdapter.createFromResource(
            this,
            R.array.type_sensors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val sm = getSystemService(SENSOR_SERVICE) as SensorManager
        var text = ""
        sm.getSensorList(Sensor.TYPE_ALL).forEach {
            text += "${it.name}\n${it.type}\n\n"
        }
        text = text.trim()

        listSensor.text = text
    }
}