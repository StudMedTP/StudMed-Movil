package com.example.studmed.Docente.evaluaciones

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarEvaluacionBinding.inflate(layoutInflater)

        setContentView(binding.root)


        cargarSecciones()

        binding.Seccion.setOnClickListener {
            selectSecciones()
        }



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