package com.example.studmed.Docente.Nav_Fragments_Docente

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.studmed.R
import com.example.studmed.databinding.FragmentSeccionesDBinding
import com.google.firebase.database.FirebaseDatabase

class FragmentSeccionesD : Fragment() {

    private lateinit var binding : FragmentSeccionesDBinding
    private lateinit var mContext: Context
    private lateinit var progressDialog: ProgressDialog

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        binding = FragmentSeccionesDBinding.inflate(inflater, container, false)


        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnAgregarSeccion.setOnClickListener {
            validarInfo()
        }

        return binding.root
    }

    private var seccion = ""
    private fun validarInfo() {
        seccion = binding.etSeccionD.text.toString().trim()
        if (seccion.isEmpty()){
            Toast.makeText(context,"Ingrese una seccion", Toast.LENGTH_SHORT).show()
        }else{
            agregarSeccionBD()
        }

    }

    private fun agregarSeccionBD() {
        progressDialog.setMessage("Agregando sección")
        progressDialog.show()

        var ref = FirebaseDatabase.getInstance().getReference("Secciones")
        var keyId = ref.push().key

        val hashMap = HashMap<String, Any>()
        hashMap["id"] = "${keyId}"
        hashMap["seccion"] = "${seccion}"

        ref.child(keyId!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(context,"Se agrego la sección con éxito", Toast.LENGTH_SHORT).show()
                binding.etSeccionD.setText("")
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(context,"${e.message}", Toast.LENGTH_SHORT).show()
            }




    }


}