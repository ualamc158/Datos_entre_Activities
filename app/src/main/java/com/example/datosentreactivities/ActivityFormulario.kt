import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter // Importante para el Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.datosentreactivities.MainActivity
import com.example.datosentreactivities.databinding.ActivityFormularioBinding

class ActivityFormulario : AppCompatActivity() {

    lateinit var binding: ActivityFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idiomas = arrayOf("Español", "Inglés", "Francés")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idiomas)
        binding.spinnerIdioma.adapter = adapter

        binding.botonEnviar.setOnClickListener { result() }

        binding.botonReset.setOnClickListener {
            binding.editTextName.text.clear()
            binding.seekBarEdad.progress = 0
            binding.spinnerIdioma.setSelection(0)
        }
    }

    private fun result() {
        val nombre = binding.editTextName.text.toString()

        if (nombre.isNotEmpty()) {

            val intent = Intent(this, MainActivity::class.java)

            val edad = binding.seekBarEdad.progress
            val idioma = binding.spinnerIdioma.selectedItem.toString()

            intent.putExtra("EXTRA_NAME", nombre)
            intent.putExtra("EXTRA_EDAD", edad)
            intent.putExtra("EXTRA_IDIOMA", idioma)

            startActivity(intent)
        } else {

            binding.editTextName.error = "El nombre es obligatorio"
        }
    }
}