package com.palash.abient_light_sensor.repository

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class SensorRepository @Inject constructor(private val sensorManager: SensorManager) {

    private val _lightData = MutableLiveData<Float?>()
    val lightData: LiveData<Float?> = _lightData

    private val lightListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                if (it.sensor.type == Sensor.TYPE_LIGHT) {
                    _lightData.postValue(it.values[0])
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No action needed
        }
    }

    fun startListening() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (sensor != null) {
            sensorManager.registerListener(lightListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            _lightData.postValue(null)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(lightListener)
    }
}