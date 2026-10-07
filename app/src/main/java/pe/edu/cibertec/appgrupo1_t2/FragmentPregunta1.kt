package pe.edu.cibertec.appgrupo1_t2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.cibertec.appgrupo1_t2.databinding.FragmentPregunta1Binding
import java.util.Locale

class FragmentPregunta1 : Fragment(), View.OnClickListener {

    private var _binding: FragmentPregunta1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPregunta1Binding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v?.id == binding.btnCalcular.id) {

            val pesoTexto = binding.etPeso.text.toString().trim()

            if (pesoTexto.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Ingrese el peso de la mascota",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val peso = pesoTexto.toDoubleOrNull()

            if (peso == null || peso < 0) {
                Toast.makeText(
                    requireContext(),
                    "Ingrese un peso válido",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            if (peso <= 8.0) {

                binding.tvResultado.text =
                    "Mascota apta para viajar en cabina sin sobrecosto."

            } else {

                val exceso = peso - 8.0

                val recargo = 150.0 + (exceso * 35.0)

                binding.tvResultado.text = String.format(
                    Locale.US,
                    "Peso total ingresado: %.2f kg\n" +
                            "Exceso de peso: %.2f kg\n" +
                            "Monto total a pagar por recargo: S/ %.2f",
                    peso,
                    exceso,
                    recargo
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}