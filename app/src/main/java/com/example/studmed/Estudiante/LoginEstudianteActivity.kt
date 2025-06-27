package com.example.studmed.Estudiante

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.R
import com.example.studmed.databinding.ActivityLoginEstudianteBinding

class LoginEstudianteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginEstudianteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEstudianteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegistrarE.setOnClickListener {
            startActivity(Intent(this@LoginEstudianteActivity, RegistroEstudianteActivity::class.java))
        }
    }
}