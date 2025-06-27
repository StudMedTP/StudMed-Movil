package com.example.studmed

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.Docente.LoginDocenteActivity
import com.example.studmed.Estudiante.LoginEstudianteActivity
import com.example.studmed.databinding.ActivitySeleccionarTipoBinding

class SeleccionarTipoActivity : AppCompatActivity() {


    private lateinit var binding : ActivitySeleccionarTipoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionarTipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipoDocente.setOnClickListener {
            startActivity(Intent(this@SeleccionarTipoActivity, LoginDocenteActivity::class.java))
        }

        binding.tipoEstudiante.setOnClickListener {
            startActivity(Intent(this@SeleccionarTipoActivity, LoginEstudianteActivity::class.java))
        }
    }
}