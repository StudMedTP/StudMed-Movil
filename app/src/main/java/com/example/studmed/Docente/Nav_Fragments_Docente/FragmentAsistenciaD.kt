package com.example.studmed.Docente.Nav_Fragments_Docente

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.studmed.R
import java.text.SimpleDateFormat
import java.util.*

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
        val codigo = obtenerCodigoDelDia()

        val mensaje = "El código del día de hoy es: $codigo"

        AlertDialog.Builder(requireContext())
            .setTitle("Código del día")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun obtenerCodigoDelDia(): Int {
        val formatoFecha = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val fecha = formatoFecha.format(Date())
        val seed = fecha.hashCode().toLong()
        val random = Random(seed)
        return random.nextInt(9000) + 1000 // Código entre 1000 y 9999
    }
}
