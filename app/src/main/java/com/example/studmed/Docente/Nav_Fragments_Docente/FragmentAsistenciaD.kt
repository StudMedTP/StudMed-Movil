package com.example.studmed.Docente.Nav_Fragments_Docente

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.studmed.R
import kotlin.random.Random

class FragmentAsistenciaD : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asistencia_d, container, false)

        val btnVerCodigo = view.findViewById<Button>(R.id.btnVerCodigo)

        btnVerCodigo.setOnClickListener {
            mostrarCodigoDelDia()
        }

        return view
    }

    private fun mostrarCodigoDelDia() {
        val codigo = (1000..9999).random()

        //val mensaje = "El código del día de hoy es:\n\n$codigo"/
        val mensaje = "El código del día de hoy es:5698"

        AlertDialog.Builder(requireContext())
            .setTitle("Código del día")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}


