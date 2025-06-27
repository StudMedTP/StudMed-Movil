package com.example.studmed.Docente.Nav_Fragments_Docente

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studmed.Docente.evaluaciones.AgregarEvaluacionActivity
import com.example.studmed.R
import com.example.studmed.databinding.FragmentEvaluacionesDBinding

class FragmentEvaluacionesD : Fragment() {

    private lateinit var binding : FragmentEvaluacionesDBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentEvaluacionesDBinding.inflate(inflater, container, false)


        binding.addEvaluacion.setOnClickListener {
            startActivity(Intent(context, AgregarEvaluacionActivity::class.java))
        }

        return binding.root










    }

}