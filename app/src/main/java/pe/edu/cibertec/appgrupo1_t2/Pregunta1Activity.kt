package pe.edu.cibertec.appgrupo1_t2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1_t2.databinding.ActivityPregunta1Binding

data class Usuario(
    val identificador: String,
    val contrasena: String
)

class Pregunta1Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta1Binding

    private val listaUsuarios = listOf(
        Usuario("i202509411", "72946057"),
        Usuario("i202403719", "75953422"),
        Usuario("i202501385", "76547406"),
        Usuario("i202021859", "71100685"),
        Usuario("i202124294", "76928405"),
        Usuario("i202504257", "75150069"),
        Usuario("i202506143", "75109416")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btnIngresar) {
            val usuarioIngresado = binding.edtUsuario.text.toString().trim()
            val contrasenaIngresada = binding.edtContrasena.text.toString().trim()

            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor, complete ambos campos", Toast.LENGTH_SHORT).show()
                return
            }

            val usuarioValido = verificarCredenciales(usuarioIngresado, contrasenaIngresada)

            if (usuarioValido) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verificarCredenciales(usuario: String, contrasena: String): Boolean {
        return listaUsuarios.any {
            it.identificador == usuario && it.contrasena == contrasena
        }
    }
}