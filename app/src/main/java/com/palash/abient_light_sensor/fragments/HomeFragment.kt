package com.palash.abient_light_sensor.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.palash.abient_light_sensor.R
import com.palash.abient_light_sensor.databinding.FragmentHomeBinding
import com.palash.abient_light_sensor.view_models.SensorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sensorViewModel by viewModels<SensorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sensorViewModel.lightData.observe(viewLifecycleOwner, Observer { light ->
            if (light != null) {
                binding.txt.text = "Ambient Light: $light lx"
            } else {
                binding.txt.text = "Light sensor not available"
                Toast.makeText(
                    context,
                    "Light sensor not available on this device",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        sensorViewModel.startListening()
    }

    override fun onPause() {
        super.onPause()
        sensorViewModel.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}