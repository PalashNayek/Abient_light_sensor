package com.palash.abient_light_sensor.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.palash.abient_light_sensor.repository.SensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(private val sensorRepository: SensorRepository) : ViewModel() {

    val lightData: LiveData<Float?> = sensorRepository.lightData

    fun startListening() {
        sensorRepository.startListening()
    }

    fun stopListening() {
        sensorRepository.stopListening()
    }
}