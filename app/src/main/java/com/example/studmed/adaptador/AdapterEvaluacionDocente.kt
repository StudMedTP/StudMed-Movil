package com.example.studmed.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studmed.R
import com.example.studmed.modelos.ModeloEvaluacion

class AdapterEvaluacionDocente(private val evaluacionesList: ArrayList<ModeloEvaluacion>) :
    RecyclerView.Adapter<AdapterEvaluacionDocente.HolderEvaluacionDocente>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderEvaluacionDocente {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evaluacion_d, parent, false)
        return HolderEvaluacionDocente(view)
    }

    override fun onBindViewHolder(holder: HolderEvaluacionDocente, position: Int) {
        val evaluacion = evaluacionesList[position]
        holder.titulo.text = evaluacion.titulo
        holder.seccion.text = "Sección: ${evaluacion.seccion}"
        holder.descripcion.text = "Descripción: ${evaluacion.descripcion}"
    }

    override fun getItemCount(): Int = evaluacionesList.size

    class HolderEvaluacionDocente(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.item_nombre_evaluacion_d)
        val seccion: TextView = itemView.findViewById(R.id.item_seccion_evaluacion_d)
        val descripcion: TextView = itemView.findViewById(R.id.item_descripcion_evaluacion_d)
    }
}
