package com.example.studmed.Estudiante

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.Docente.MainActivityDocente
import com.example.studmed.R


class MainActivityEstudiante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_estudiante)

        verBienvenida()

    }

    private fun verBienvenida() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                startActivity(Intent(applicationContext, MainActivityDocente::class.java))
                finishAffinity()
            }

            override fun onTick(p0: Long) {

            }

        }.start()
    }
}