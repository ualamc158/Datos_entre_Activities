package com.example.datosentreactivities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// 1. Importa la clase Binding correcta (generada desde activity_main.xml)
import com.example.datosentreactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 2. Declara la variable de binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 3. Infla el binding ANTES de setContentView
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 4. Usa el 'root' del binding para setContentView
        setContentView(binding.root)

        // 5. Usa el binding para acceder a las vistas (reemplaza findViewById)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Mantenemos tu lógica para recibir datos ---
        val nombre = intent.getStringExtra("EXTRA_NAME")
        val edad = intent.getIntExtra("EXTRA_EDAD", 0) // 0 es valor por defecto
        val idioma = intent.getStringExtra("EXTRA_IDIOMA")

        // Comprobamos si recibimos datos (para no hacer esto la primera vez que se abre)
        if (nombre != null) {
            // Mostramos los datos en el Log
            Log.d("MainActivity", "Nombre recibido: $nombre")
            Log.d("MainActivity", "Edad recibida: $edad")
            Log.d("MainActivity", "Idioma recibido: $idioma")

            // --- ¡AQUÍ USAS EL BINDING! ---
            // Si añades TextViews a tu activity_main.xml (ej: con id 'tvNombre', 'tvEdad'),
            // puedes mostrar los datos así:

            // binding.tvNombre.text = "Nombre: $nombre"
            // binding.tvEdad.text = "Edad: $edad"
            // binding.tvIdioma.text = "Idioma: $idioma"
        }
    }
}