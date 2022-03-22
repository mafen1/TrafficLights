package com.example.trafficlights

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.trafficlights.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    var seconds = 15002L
    val TAG = "TAG"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { TraficLights() }


    }
    private fun TraficLights(){
        object : CountDownTimer(seconds, 1000L) {
            override fun onTick(p0: Long) {
                Log.d(TAG, "${p0/1000}")
                binding.button.isClickable = false
                when (p0/1000) {
                    15L -> binding.redLight.setBackgroundColor(Color.RED)
                    10L -> {
                        binding.redLight.setBackgroundColor(Color.BLACK)
                        binding.yellowLight.setBackgroundColor(Color.YELLOW)
                    }
                    5L -> {
                        binding.redLight.setBackgroundColor(Color.BLACK)
                        binding.yellowLight.setBackgroundColor(Color.BLACK)
                        binding.greenLight.setBackgroundColor(Color.GREEN)
                    }
                }
            }

            override fun onFinish() {
                binding.greenLight.setBackgroundColor(Color.BLACK)
                binding.button.isClickable = true
                Snackbar.make(
                    binding.root,
                    "Успешно",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }.start()
    }
}
