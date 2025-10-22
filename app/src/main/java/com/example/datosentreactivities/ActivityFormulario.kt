package com.example.datosentreactivities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datosentreactivities.databinding.ActivityFormularioBinding

class ActivityFormulario : AppCompatActivity() {

    lateinit var binding: ActivityFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adapter personalizado para el Spinner
        val idiomas = arrayOf("Elige un idioma...", "Español", "Inglés", "Francés")

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idiomas) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    view.setTextColor(Color.BLACK)
                }
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIdioma.adapter = adapter


        // Listener del SeekBar
        binding.seekBarEdad.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewEdadModificada.text = "Edad: $progress años"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.botonEnviar.setOnClickListener { result() }

        binding.botonReset.setOnClickListener {
            binding.editTextName.text.clear()
            binding.seekBarEdad.progress = 0
            binding.spinnerIdioma.setSelection(0)
            binding.textViewEdadModificada.text = "Edad: 0 años"
        }
    }

    private fun result() {
        val nombre = binding.editTextName.text.toString().trim()
        val idiomaPosition = binding.spinnerIdioma.selectedItemPosition

        // Validaciones
        val isNombreValid = nombre.length >= 3
        val isIdiomaValid = idiomaPosition > 0

        // Resetea error previo del EditText
        binding.editTextName.error = null

        if (isNombreValid && isIdiomaValid) {
            // Éxito en las validaciones: Enviar datos
            val intent = Intent(this, MainActivity::class.java)

            val edad = binding.seekBarEdad.progress
            val idioma = binding.spinnerIdioma.selectedItem.toString()

            intent.putExtra("EXTRA_NAME", nombre)
            intent.putExtra("EXTRA_EDAD", edad)
            intent.putExtra("EXTRA_IDIOMA", idioma)

            startActivity(intent)
        } else {
            // Fallo en las validaciones: Mostrar errores
            if (!isNombreValid) {
                binding.editTextName.error = "El nombre debe tener al menos 3 caracteres"
            }

            if (!isIdiomaValid) {
                // Mostramos un Toast
                Toast.makeText(this, "Debes seleccionar un idioma!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}