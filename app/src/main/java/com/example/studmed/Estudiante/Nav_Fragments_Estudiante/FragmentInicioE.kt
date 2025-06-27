package com.example.studmed.Estudiante.Nav_Fragments_Estudiante

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studmed.Estudiante.Bottom_Nav_Fragments_Estudiante.FragmentPerfilE
import com.example.studmed.Estudiante.Bottom_Nav_Fragments_Estudiante.FragmentSettingsE
import com.example.studmed.Estudiante.Bottom_Nav_Fragments_Estudiante.FragmentSupportE
import com.example.studmed.R
import com.example.studmed.databinding.FragmentInicioEBinding

class FragmentInicioEstudiante : Fragment() {

    private lateinit var binding : FragmentInicioEBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInicioEBinding.inflate(inflater,container,false)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.op_perfil_e->{
                    replaceFragment(FragmentPerfilE())
                }
                R.id.op_support_e->{
                    replaceFragment(FragmentSupportE())
                }
                R.id.op_settings_e->{
                    replaceFragment(FragmentSettingsE())
                }
            }

            true
        }

        replaceFragment(FragmentPerfilE())
        binding.bottomNavigation.selectedItemId = R.id.op_perfil_e

        return binding.root
    }

    private fun replaceFragment(fragment : Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment,fragment)
            .commit()
    }

}