package com.example.studmed.Estudiante.Nav_Fragments_Estudiante

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.studmed.R
import com.example.studmed.adaptadores.AdapterEvaluacionEstudiante
import com.example.studmed.modelos.ModeloEvaluacion
import com.google.firebase.database.*

class FragmentEvaluacionesEstudiante : Fragment() {

    private lateinit var rvSecciones: RecyclerView
    private lateinit var evaluacionesList: ArrayList<ModeloEvaluacion>
    private lateinit var adapterEvaluacion: AdapterEvaluacionEstudiante
    private lateinit var databaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asegúrate de que este nombre coincida exactamente con el archivo XML real
        val view = inflater.inflate(R.layout.fragment_evaluaciones_estudiante, container, false)

        // CORREGIDO: findViewById debe usarse con 'view' en Fragments
        rvSecciones = view.findViewById(R.id.rvSecciones)

        evaluacionesList = ArrayList()
        adapterEvaluacion = AdapterEvaluacionEstudiante(evaluacionesList)
        rvSecciones.adapter = adapterEvaluacion

        databaseRef = FirebaseDatabase.getInstance().getReference("Evaluaciones")
        cargarEvaluaciones()

        return view
    }

    private fun cargarEvaluaciones() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                evaluacionesList.clear()
                for (ds in snapshot.children) {
                    val modelo = ds.getValue(ModeloEvaluacion::class.java)
                    if (modelo != null) {
                        evaluacionesList.add(modelo)
                    }
                }
                adapterEvaluacion.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores si deseas
            }
        })
    }
}
