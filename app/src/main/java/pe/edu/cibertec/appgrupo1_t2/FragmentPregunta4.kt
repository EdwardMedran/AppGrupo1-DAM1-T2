package pe.edu.cibertec.appgrupo1_t2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentPregunta4 : Fragment() {

    private lateinit var recyclerProducts: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pregunta4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerProducts = view.findViewById(R.id.recyclerProducts)
        progressBar = view.findViewById(R.id.progressBar)

        recyclerProducts.layoutManager = LinearLayoutManager(requireContext())

        obtenerProductos()
    }

    private fun obtenerProductos() {
        progressBar.visibility = View.VISIBLE

        RetrofitClient.api.getProducts().enqueue(object : Callback<ProductResponse> {

            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val productos = response.body()?.products ?: emptyList()
                    recyclerProducts.adapter = ProductAdapter(productos)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error al obtener productos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                progressBar.visibility = View.GONE

                Toast.makeText(
                    requireContext(),
                    "Error de conexión: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}