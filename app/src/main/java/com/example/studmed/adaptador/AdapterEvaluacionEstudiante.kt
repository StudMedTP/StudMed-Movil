package com.example.studmed.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studmed.R
import com.example.studmed.modelos.ModeloEvaluacion

class AdapterEvaluacionEstudiante(private val evaluacionesList: ArrayList<ModeloEvaluacion>) :
    RecyclerView.Adapter<AdapterEvaluacionEstudiante.HolderEvaluacion>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderEvaluacion {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evaluacion_e, parent, false)
        return HolderEvaluacion(view)
    }

    override fun onBindViewHolder(holder: HolderEvaluacion, position: Int) {
        val evaluacion = evaluacionesList[position]
        holder.tvTitulo.text = evaluacion.titulo
        holder.tvSeccion.text = "Sección: ${evaluacion.seccion}"
        holder.tvDescripcion.text = "Descripción: ${evaluacion.descripcion}"
    }

    override fun getItemCount(): Int = evaluacionesList.size

    class HolderEvaluacion(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.item_nombre_evaluacion_e)
        val tvSeccion: TextView = itemView.findViewById(R.id.tvSeccionEvaluacion)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionEvaluacion)
    }
}

