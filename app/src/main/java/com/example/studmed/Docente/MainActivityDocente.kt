package com.example.studmed.Docente

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentPerfilD
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentSettingsD
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentSupportD
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentAsistenciaD
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentEvaluacionesD
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentInicioD
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentNotificacionesD
import com.example.studmed.R
import com.example.studmed.databinding.ActivityMainDocenteBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivityDocente : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{


    private lateinit var binding : ActivityMainDocenteBinding
    private var firebaseAuth : FirebaseAuth?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDocenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        firebaseAuth = FirebaseAuth.getInstance()
        comprobarSesion()

        binding.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(FragmentInicioD())
        binding.navigationView.setCheckedItem(R.id.op_inicio_d)
    }

    private fun cerrarSesion(){
        firebaseAuth!!.signOut()
        startActivity(Intent(applicationContext, LoginDocenteActivity::class.java))
        finish()
        Toast.makeText(applicationContext, "Has cerrado sesión", Toast.LENGTH_SHORT).show()
    }

    private fun comprobarSesion() {
        /*Si el usuario no ha iniciado sesion, que lo diriga a OpcionesLogin*/
        if (firebaseAuth!!.currentUser==null){
            startActivity(Intent(applicationContext, LoginDocenteActivity::class.java))
            Toast.makeText(applicationContext, "Docente no registrado o no logeado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Docente en linea", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.op_inicio_d->{
                replaceFragment(FragmentInicioD())
            }

            R.id.op_control_asistencia_d->{
                replaceFragment(FragmentAsistenciaD())
            }
            R.id.op_evaluaciones_d->{
                replaceFragment(FragmentEvaluacionesD())
            }
            R.id.op_notificaciones_d->{
                replaceFragment(FragmentNotificacionesD())
            }
            R.id.op_cerrar_sesion_d->{
                cerrarSesion()
            }
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
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}