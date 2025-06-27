package com.example.studmed.Docente

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.Constantes

import com.example.studmed.R
import com.example.studmed.databinding.ActivityRegistroDocenteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistroDocenteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroDocenteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroDocenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrarD.setOnClickListener{
            validarInformacion()
        }

    }

    private var nombres = ""
    private var email = ""
    private var password = ""
    private var cpassword = ""
    private fun validarInformacion() {
        nombres = binding.etNombresD.text.toString().trim()
        email = binding.etEmailD.text.toString().trim()
        password = binding.etPasswordD.text.toString().trim()
        cpassword = binding.etCPasswordD.text.toString().trim()

        if (nombres.isEmpty()){
            binding.etNombresD.error = "Ingrese sus nombres"
            binding.etNombresD.requestFocus()
        } else if (email.isEmpty()){
            binding.etEmailD.error = "Ingrese su email"
            binding.etEmailD.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailD.error = "Email no válido"
            binding.etEmailD.requestFocus()
        } else if (password.isEmpty()){
            binding.etPasswordD.error = "Ingrese su password"
            binding.etPasswordD.requestFocus()
        } else if (password.length < 6){
            binding.etPasswordD.error = "Necesita 6 o más caracteres"
            binding.etPasswordD.requestFocus()
        } else if (cpassword.isEmpty()){
            binding.etCPasswordD.error = "Confirme su password"
            binding.etCPasswordD.requestFocus()
        } else if (password!=cpassword){
            binding.etCPasswordD.error = "No coincide los passwords"
            binding.etCPasswordD.requestFocus()
        } else {
            registrarDocente()
        }
    }

    private fun registrarDocente() {
        progressDialog.setMessage("Creando cuenta")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                insertarInfoBD()
            }
            .addOnFailureListener { e->
                Toast.makeText(this, "Falló el registro debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }


    }

    private fun insertarInfoBD(){
        progressDialog.setMessage("Guardando información...")

        val uidBD = firebaseAuth.uid
        val nombreBD = nombres
        val emailBD = email
        val tipoUsuario = "docente"
        val tiempoBD = Constantes().obtenerTiempoD()

        val datosDocente = HashMap<String, Any>()

        datosDocente["uid"] = "$uidBD"
        datosDocente["nombres"] = "$nombreBD"
        datosDocente["email"] = "$emailBD"
        datosDocente["tipoUsuario"] = "docente"
        datosDocente["tiempo_registro"] = tiempoBD

        val references = FirebaseDatabase.getInstance().getReference("Usuarios")
        references.child(uidBD!!)
            .setValue(datosDocente)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivityDocente::class.java))
                finish()
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(this, "Falló el registro en BD debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}