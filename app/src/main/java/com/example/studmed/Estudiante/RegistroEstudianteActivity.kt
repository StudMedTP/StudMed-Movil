package com.example.studmed.Estudiante

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studmed.Constantes
import com.example.studmed.R
import com.example.studmed.databinding.ActivityRegistroEstudianteBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistroEstudianteActivity : AppCompatActivity() {


    private lateinit var binding : ActivityRegistroEstudianteBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroEstudianteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrarE.setOnClickListener {
            validarInformacion()
        }
    }

    private var nombres = ""
    private var email = ""
    private var password = ""
    private var cpassword = ""
    private fun validarInformacion() {

        nombres = binding.etNombresE.text.toString().trim()
        email = binding.etEmailE.text.toString().trim()
        password = binding.etPasswordE.text.toString().trim()
        cpassword = binding.etCPasswordE.text.toString().trim()

        if (nombres.isEmpty()){
            binding.etNombresE.error = "Ingrese nombres"
            binding.etNombresE.requestFocus()
        } else if (email.isEmpty()){
            binding.etEmailE.error = "Ingrese email"
            binding.etEmailE.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailE.error = "Email no válido"
            binding.etEmailE.requestFocus()
        }else if (password.isEmpty()){
            binding.etPasswordE.error = "Ingrese password"
            binding.etPasswordE.requestFocus()
        }else if (password.length < 6){
            binding.etPasswordE.error = "Necesita más de 6 car."
            binding.etPasswordE.requestFocus()
        }else if (cpassword.isEmpty()){
            binding.etCPasswordE.error = "Confirme password"
            binding.etCPasswordE.requestFocus()
        }else if (password!=cpassword){
            binding.etCPasswordE.error = "No coinciden los password"
            binding.etCPasswordE.requestFocus()
        }else{
            registrarEstudiante()
        }

    }

    private fun registrarEstudiante() {
        progressDialog.setMessage("Creando cuenta")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                insertarInfoBD()
            }
            .addOnFailureListener {e->
                Toast.makeText(this, "Fallo el registro debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun insertarInfoBD(){
        progressDialog.setMessage("Guardando información")

        val uid = firebaseAuth.uid
        val nombresE = nombres
        val emailE = email
        val tiempoRegistro = Constantes().obtenerTiempoD()

        val datosEstudiante = HashMap<String, Any>()

        datosEstudiante["uid"] = "$uid"
        datosEstudiante["nombres"] = "$nombresE"
        datosEstudiante["email"] = "$emailE"
        datosEstudiante["tRegistro"] = "$tiempoRegistro"
        datosEstudiante["imagen"] = ""
        datosEstudiante["tipoUsuario"] = "estudiante"

        val reference = FirebaseDatabase.getInstance().getReference("Usuarios")
        reference.child(uid!!)
            .setValue(datosEstudiante)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this@RegistroEstudianteActivity, MainActivityEstudiante::class.java))
                finishAffinity()
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(this, "Fallo el registro debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}