package com.example.studmed.Estudiante

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentPerfilD
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentSettingsD
import com.example.studmed.Docente.Botton_Nav_Fragments_Docente.FragmentSupportD
import com.example.studmed.Docente.MainActivityDocente
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentAsistenciaD
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentEvaluacionesD
import com.example.studmed.Docente.Nav_Fragments_Docente.FragmentNotificacionesD
import com.example.studmed.Estudiante.Bottom_Nav_Fragments_Estudiante.FragmentPerfilE
import com.example.studmed.Estudiante.Bottom_Nav_Fragments_Estudiante.FragmentSettingsE
import com.example.studmed.Estudiante.Bottom_Nav_Fragments_Estudiante.FragmentSupportE
import com.example.studmed.Estudiante.Nav_Fragments_Estudiante.FragmentAsistenciaEstudiante
import com.example.studmed.Estudiante.Nav_Fragments_Estudiante.FragmentEvaluacionesEstudiante
import com.example.studmed.Estudiante.Nav_Fragments_Estudiante.FragmentInicioEstudiante
import com.example.studmed.Estudiante.Nav_Fragments_Estudiante.FragmentNotificacionesEstudiante
import com.example.studmed.R
import com.example.studmed.SeleccionarTipoActivity
import com.example.studmed.databinding.ActivityMainEstudianteBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivityEstudiante : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainEstudianteBinding
    private var firebaseAuth : FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainEstudianteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
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

        replaceFragment(FragmentInicioEstudiante())
        binding.navigationView.setCheckedItem(R.id.op_inicio_e)


        verBienvenida()

    }

    private fun comprobarSesion(){
        if(firebaseAuth!!.currentUser==null){
            startActivity(Intent(applicationContext, SeleccionarTipoActivity::class.java))
        }else{
            Toast.makeText(this, "Usuario en linea", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cerrarSesion(){
        firebaseAuth!!.signOut()
        startActivity(Intent(this@MainActivityEstudiante, SeleccionarTipoActivity::class.java))
        finishAffinity()
        Toast.makeText(this, "Cerraste sesión", Toast.LENGTH_SHORT).show()
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment,fragment)
            .commit()
    }

    private fun verBienvenida() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                startActivity(Intent(applicationContext, MainActivityEstudiante::class.java))
                finishAffinity()
            }

            override fun onTick(p0: Long) {

            }

        }.start()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.op_inicio_e->{
                replaceFragment(FragmentInicioEstudiante())
            }
            R.id.op_control_asistencia_e->{
                replaceFragment(FragmentAsistenciaEstudiante())
            }
            R.id.op_evaluaciones_e->{
                replaceFragment(FragmentEvaluacionesEstudiante())
            }
            R.id.op_notificaciones_e->{
                replaceFragment(FragmentNotificacionesEstudiante())
            }
            R.id.op_cerrar_sesion_e->{
                cerrarSesion()
            }
            R.id.op_support_e->{
                replaceFragment(FragmentSupportE())
            }
            R.id.op_settings_e->{
                replaceFragment(FragmentSettingsE())
            }
            R.id.op_perfil_e->{
                replaceFragment(FragmentPerfilE())
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}