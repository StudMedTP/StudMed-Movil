package com.example.studmed.Docente.Nav_Fragments_Docente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentPerfilD
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentSettingsD
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentSupportD
import com.example.studmed.R
import com.example.studmed.databinding.FragmentInicioDBinding

class FragmentInicioD : Fragment() {

    private lateinit var binding : FragmentInicioDBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInicioDBinding.inflate(inflater,container,false)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.op_support_d->{
                    replaceFragment(FragmentSupportD())
                }
                R.id.op_settings_d->{
                    replaceFragment(FragmentSettingsD())
                }
                R.id.op_perfil_d->{
                    replaceFragment(FragmentPerfilD())
                }
            }

            true
        }

        replaceFragment(FragmentSupportD())
        binding.bottomNavigation.selectedItemId = R.id.op_support_d

        return binding.root


    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment, fragment)
            .commit()
    }

}