package com.example.studmed.Docente.Nav_Fragments_Docente

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studmed.Docente.evaluaciones.AgregarEvaluacionActivity
import com.example.studmed.adaptadores.AdapterEvaluacionDocente
import com.example.studmed.databinding.FragmentEvaluacionesDBinding
import com.example.studmed.modelos.ModeloEvaluacion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FragmentEvaluacionesD : Fragment() {

    private lateinit var binding: FragmentEvaluacionesDBinding
    private lateinit var evaluacionList: ArrayList<ModeloEvaluacion>
    private lateinit var adapterEvaluacion: AdapterEvaluacionDocente
    private lateinit var databaseRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEvaluacionesDBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid ?: ""

        databaseRef = FirebaseDatabase.getInstance().getReference("Evaluaciones")

        // 🔁 Usamos rvSeccionesd según tu XML
        binding.rvSeccionesd.layoutManager = LinearLayoutManager(context)
        evaluacionList = ArrayList()
        adapterEvaluacion = AdapterEvaluacionDocente(evaluacionList)
        binding.rvSeccionesd.adapter = adapterEvaluacion

        cargarEvaluaciones()

        binding.addEvaluacion.setOnClickListener {
            startActivity(Intent(context, AgregarEvaluacionActivity::class.java))
        }

        return binding.root
    }

    private fun cargarEvaluaciones() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                evaluacionList.clear()
                for (dataSnap in snapshot.children) {
                    val evaluacion = dataSnap.getValue(ModeloEvaluacion::class.java)
                    if (evaluacion != null) {
                        evaluacionList.add(evaluacion)
                    }
                    /*
                    evaluacion?.let {
                        evaluacionList.add(it)
                    }*/
                }
                adapterEvaluacion.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error al cargar evaluaciones", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
