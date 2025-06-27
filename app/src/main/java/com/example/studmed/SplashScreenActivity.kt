package com.example.studmed

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.Estudiante.MainActivityEstudiante


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        verBienvenida()

    }

    private fun verBienvenida() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                startActivity(Intent(applicationContext, MainActivityEstudiante::class.java))
                finishAffinity()
            }

            override fun onTick(p0: Long) {

            }

        }.start()
    }
}