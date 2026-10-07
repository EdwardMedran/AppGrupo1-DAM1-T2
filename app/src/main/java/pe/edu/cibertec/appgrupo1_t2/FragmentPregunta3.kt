package pe.edu.cibertec.appgrupo1_t2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.cibertec.appgrupo1_t2.databinding.FragmentPregunta3Binding
import kotlin.random.Random

class FragmentPregunta3 : Fragment(), View.OnClickListener {

    private var _binding: FragmentPregunta3Binding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AnimalAdapter

    // (nombre, palabra en inglés para buscar la foto, descripción)
    private val datosAnimales = listOf(
        Triple("León", "lion", "El rey de la selva, vive en manada."),
        Triple("Tigre", "tiger", "El felino más grande del mundo."),
        Triple("Elefante", "elephant", "El mamífero terrestre más grande."),
        Triple("Jirafa", "giraffe", "Tiene el cuello más largo de todos."),
        Triple("Cebra", "zebra", "Sus rayas son únicas en cada ejemplar."),
        Triple("Oso", "bear", "Fuerte y gran amante de la miel."),
        Triple("Lobo", "wolf", "Cazador que vive y caza en manada."),
        Triple("Zorro", "fox", "Astuto, de cola larga y esponjosa."),
        Triple("Perro", "dog", "El mejor amigo del hombre."),
        Triple("Gato", "cat", "Ágil, curioso e independiente."),
        Triple("Caballo", "horse", "Veloz y fiel compañero de trabajo."),
        Triple("Vaca", "cow", "Nos da la leche que tomamos."),
        Triple("Delfín", "dolphin", "Mamífero marino muy inteligente."),
        Triple("Tiburón", "shark", "Gran depredador de los océanos."),
        Triple("Águila", "eagle", "Ave de vista increíble y vuelo majestuoso."),
        Triple("Loro", "parrot", "Colorido, puede imitar la voz humana."),
        Triple("Pingüino", "penguin", "Ave que no vuela pero nada muy bien."),
        Triple("Tortuga", "turtle", "Lenta, de caparazón duro y larga vida."),
        Triple("Cocodrilo", "crocodile", "Reptil de mandíbula poderosa."),
        Triple("Canguro", "kangaroo", "Salta y lleva a sus crías en una bolsa.")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPregunta3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AnimalAdapter(crearListaAnimales())
        binding.rvAnimales.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAnimales.adapter = adapter

        binding.btnCambiarImagenes.setOnClickListener(this)
    }

    private fun crearListaAnimales(): List<Animal> {
        val semilla = Random.nextInt(1, 100000)
        return datosAnimales.mapIndexed { indice, (nombre, palabra, descripcion) ->
            Animal(
                nombre = nombre,
                descripcion = descripcion,
                imagenUrl = "https://picsum.photos/400/300?random=${semilla + indice}"
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCambiarImagenes -> adapter.actualizar(crearListaAnimales())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}