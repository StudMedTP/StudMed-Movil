package com.example.studmed.Docente.evaluaciones

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

import com.example.studmed.R
import com.example.studmed.databinding.ActivityAgregarEvaluacionBinding
import com.example.studmed.modelos.ModeloSeccion
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.selects.select

class AgregarEvaluacionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAgregarEvaluacionBinding

    private lateinit var seccionesArrayList: ArrayList<ModeloSeccion>

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarEvaluacionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        cargarSecciones()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.Seccion.setOnClickListener {
            selectSecciones()
        }

        binding.btnAgregarEvaluacion.setOnClickListener {
            validarInfo()
        }

    }

    private var tituloEva = ""
    private var descripcionEva = ""
    private var fechaEva = ""
    private var seccion = ""
    private var codigoEva = ""

    private fun validarInfo() {
        tituloEva = binding.etTituloEva.text.toString().trim()
        descripcionEva = binding.etDescripcionEva.text.toString().trim()
        fechaEva = binding.etFechavaEva.text.toString().trim()
        seccion = binding.Seccion.text.toString().trim()
        codigoEva = binding.etCodigoEva.text.toString().trim()

        if (tituloEva.isEmpty()){
            binding.etTituloEva.error = "Ingrese Titulo"
            binding.etTituloEva.requestFocus()
        }
        else if (descripcionEva.isEmpty()){
            binding.etDescripcionEva.error = "Ingrese descripcion"
            binding.etDescripcionEva.requestFocus()
        }
        else if (fechaEva.isEmpty()){
            binding.etFechavaEva.error = "Ingrese fecha"
            binding.etFechavaEva.requestFocus()
        }
        else if (seccion.isEmpty()){
            binding.Seccion.error = "Ingrese seccion"
            binding.Seccion.requestFocus()
        }
        else if (codigoEva.isEmpty()){
            binding.etCodigoEva.error = "Ingrese codigo"
            binding.etCodigoEva.requestFocus()
        }
        else {
            agregarEvaluacion()
        }

    }

    private fun agregarEvaluacion() {

        progressDialog.setMessage("Agregando Evaluacion")
        progressDialog.show()

        var ref = FirebaseDatabase.getInstance().getReference("Evaluaciones")
        var keyId = ref.push().key

        val hashMap= HashMap<String, Any>()

        hashMap["id"] = "${keyId}"
        hashMap["titulo"] = "${tituloEva}"
        hashMap["descripcion"] = "${descripcionEva}"
        hashMap["fecha"] = "${fechaEva}"
        hashMap["seccion"] = "${seccion}"
        hashMap["codigo"] = "${codigoEva}"

        ref.child(keyId!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this,"Se agrego la evaluacion con éxito", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
            .addOnFailureListener {e->
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarCampos(){
        binding.etTituloEva.setText("")
        binding.etDescripcionEva.setText("")
        binding.etFechavaEva.setText("")
        binding.Seccion.setText("")
        binding.etCodigoEva.setText("")

    }

    private fun cargarSecciones() {
        seccionesArrayList = ArrayList()

        val ref = FirebaseDatabase.getInstance().getReference("Secciones").orderByChild("seccion")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                seccionesArrayList.clear()
                for (ds in snapshot.children){
                    val modelo = ds.getValue(ModeloSeccion::class.java)
                    seccionesArrayList.add(modelo!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private var idSeccion = ""
    private var tituloSeccion = ""

    private fun selectSecciones(){
        val seccionesArray = arrayOfNulls<String>(seccionesArrayList.size)
        for (i in seccionesArray.indices){
            seccionesArray[i] = seccionesArrayList[i].seccion
        }

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una seccion")

            .setItems(seccionesArray){dialog, witch->

                idSeccion = seccionesArrayList[witch].id
                tituloSeccion = seccionesArrayList[witch].seccion
                binding.Seccion.text = tituloSeccion
            }
            .show()

    }
}