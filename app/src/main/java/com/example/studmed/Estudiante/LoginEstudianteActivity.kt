package com.example.studmed.Estudiante

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.R
import com.example.studmed.databinding.ActivityLoginEstudianteBinding
import com.google.firebase.auth.FirebaseAuth

class LoginEstudianteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginEstudianteBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEstudianteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnLoginE.setOnClickListener {
            validadInfo()
        }

        binding.tvRegistrarE.setOnClickListener {
            startActivity(Intent(this@LoginEstudianteActivity, RegistroEstudianteActivity::class.java))
        }
    }

    private var email = ""
    private var password = ""
    private fun validadInfo() {
        email = binding.etEmailE.text.toString().trim()
        password = binding.etPasswordE.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailE.error = "Email invalido"
            binding.etEmailE.requestFocus()
        }
        else if (email.isEmpty()){
            binding.etEmailE.error = "Ingrese email"
            binding.etEmailE.requestFocus()
        }
        else if (password.isEmpty()){
            binding.etPasswordE.error = "Ingrese password"
            binding.etPasswordE.requestFocus()
        }else{
            loginEstudiante()
        }


    }

    private fun loginEstudiante() {
        progressDialog.setMessage("Ingresando")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivityEstudiante::class.java))
                finishAffinity()
                Toast.makeText(this,"Bienvenido",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {e->
                Toast.makeText(this,"No se pudo iniciar sesion debido a ${e.message}",
                    Toast.LENGTH_SHORT).show()
            }
    }
}