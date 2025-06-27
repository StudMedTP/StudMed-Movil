package com.example.studmed.Docente

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.databinding.ActivityLoginDocenteBinding
import com.google.firebase.auth.FirebaseAuth

class LoginDocenteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginDocenteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginDocenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnLoginD.setOnClickListener {
            validarInfo()
        }

        binding.tvRegistrarD.setOnClickListener {
            startActivity(Intent(applicationContext, RegistroDocenteActivity::class.java))
        }
    }
    private var email = ""
    private var password = ""
    private fun validarInfo() {
        email = binding.etEmailD.text.toString().trim()
        password = binding.etPasswordD.text.toString().trim()

        if (email.isEmpty()){
            binding.etEmailD.error = "Ingrese email"
            binding.etEmailD.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailD.error = "Email no válido"
            binding.etEmailD.requestFocus()
        }else if (password.isEmpty()){
            binding.etPasswordD.error = "Ingrese password"
            binding.etPasswordD.requestFocus()
        }else{
            loginDocente()
        }

    }

    private fun loginDocente() {

        progressDialog.setMessage("Ingresando")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivityDocente::class.java))
                finishAffinity()
                Toast.makeText(
                    this,
                    "Bienvenido",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(
                    this,
                    "No se pudo iniciar sesión debido a ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}