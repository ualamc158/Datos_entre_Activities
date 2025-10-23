package com.example.datosentreactivities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datosentreactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("EXTRA_NAME")
        val edad = intent.getIntExtra("EXTRA_EDAD", 0) // 0 es valor por defecto
        val idioma = intent.getStringExtra("EXTRA_IDIOMA")

        if (nombre != null) {
            Log.d("MainActivity", "Nombre recibido: $nombre")
            Log.d("MainActivity", "Edad recibida: $edad")
            Log.d("MainActivity", "Idioma recibido: $idioma")


            // Asignamos los datos a los TextViews
            binding.tvNombre.text = "Nombre: $nombre"
            binding.tvEdad.text = "Edad: $edad años"
            binding.tvIdioma.text = "Idioma: $idioma"
        }
    }
}