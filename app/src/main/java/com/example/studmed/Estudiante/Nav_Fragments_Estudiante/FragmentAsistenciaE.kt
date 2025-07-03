package com.example.studmed.Estudiante.Nav_Fragments_Estudiante

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
            .setPositiveButton("Siguiente") { dialog, _ ->
                dialog.dismiss()
                mostrarDialogoCodigo()
            }
            .show()
    }

    private fun mostrarDialogoCodigo() {
        val inputLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 30, 50, 10)
        }

        val editText = EditText(requireContext()).apply {
            hint = "Ingrese código"
        }

        inputLayout.addView(editText)

        AlertDialog.Builder(requireContext())
            .setTitle("Verificación de código")
            .setMessage("Por favor, coloque el código brindado")
            .setView(inputLayout)
            .setNegativeButton("Regresar") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Aceptar") { dialog, _ ->
                val codigoIngresado = editText.text.toString().trim()
                val codigoCorrecto = obtenerCodigoDelDia().toString()

                if (codigoIngresado == codigoCorrecto) {
                    Toast.makeText(
                        requireContext(),
                        "Código correcto. Asistencia registrada correctamente.",
                        Toast.LENGTH_LONG
                    ).show()
                    // Aquí puedes agregar lógica extra si quieres
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Código incorrecto. Inténtalo nuevamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun obtenerCodigoDelDia(): Int {
        val formatoFecha = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val fecha = formatoFecha.format(Date())
        val seed = fecha.hashCode().toLong()
        val random = Random(seed)
        return random.nextInt(9000) + 1000
    }
}
