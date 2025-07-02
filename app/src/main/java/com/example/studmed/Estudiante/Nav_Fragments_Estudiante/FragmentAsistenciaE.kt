package com.example.studmed.Estudiante.Nav_Fragments_Estudiante

import android.app.AlertDialog
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.studmed.R
import java.text.SimpleDateFormat
import java.util.*

class FragmentAsistenciaE : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asistencia_e, container, false)

        val btnFirmar1 = view.findViewById<Button>(R.id.btnFirmar1)
        val btnFirmar2 = view.findViewById<Button>(R.id.btnFirmar2)

        btnFirmar1.setOnClickListener {
            mostrarDialogoConfirmacion()
        }

        btnFirmar2.setOnClickListener {
            mostrarDialogoConfirmacion()
        }

        return view
    }

    private fun mostrarDialogoConfirmacion() {
        val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val hora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        val mensaje = """
            Se registrará tu asistencia con los 
            siguientes datos:
            
            Ubicación: XXXXX, XXXXX, Lima
            
            Fecha: $fecha
            
            Hora: $hora
            
            Para continuar con su código brindado, 
            seleccione Siguiente. Si no se encuentra en la ubicación de su práctica, por favor intente más tarde cuando se encuentre en su ubicación para evitar una asistencia errónea.
        """.trimIndent()

        AlertDialog.Builder(requireContext())
            .setTitle("Confirmar asistencia")
            .setMessage(mensaje)
            .setNegativeButton("Regresar") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Siguiente") { _, _ ->
                // Aquí luego conectarás con escáner o validación
            }
            .show()
    }
}
