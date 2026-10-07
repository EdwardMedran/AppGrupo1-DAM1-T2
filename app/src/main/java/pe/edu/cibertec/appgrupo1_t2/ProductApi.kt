package pe.edu.cibertec.appgrupo1_t2

import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    fun getProducts(): Call<ProductResponse>
}