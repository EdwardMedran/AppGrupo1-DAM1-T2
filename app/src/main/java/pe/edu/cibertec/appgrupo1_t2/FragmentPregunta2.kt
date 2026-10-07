package pe.edu.cibertec.appgrupo1_t2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.cibertec.appgrupo1_t2.databinding.FragmentPregunta2Binding
import java.util.Locale

class FragmentPregunta2 : Fragment(), View.OnClickListener {

    private var _binding: FragmentPregunta2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPregunta2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v?.id == binding.btnCalcular.id) {

            val textoGramos = binding.etGramos.text.toString().trim()

            if (textoGramos.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Ingrese los gramos de comida sobrante",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val gramos = textoGramos.toDoubleOrNull()

            if (gramos == null) {
                Toast.makeText(
                    requireContext(),
                    "Ingrese un valor válido",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            if (gramos <= 100) {

                binding.tvResultado.text =
                    "Plato dentro del margen admisible de consumo."

            } else {

                val exceso = gramos - 100
                val penalizacion = 15.00 + (exceso * 0.12)

                binding.tvResultado.text = String.format(
                    Locale.US,
                    "Gramos sobrantes: %.2f g\n" +
                            "Exceso de desperdicio: %.2f g\n" +
                            "Penalización total: S/ %.2f",
                    gramos,
                    exceso,
                    penalizacion
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}