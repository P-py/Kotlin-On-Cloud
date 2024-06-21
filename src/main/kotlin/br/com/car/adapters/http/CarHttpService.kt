package br.com.car.adapters.http

import br.com.car.domain.http.CarHttp
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Service
interface CarHttpService {
    // Not the same as the GET request from spring controller
    @GET("cars-inventory")
    // @Query annotation indicates the request parameters in retrofit
    // NOT A SQL Query
    fun getByModel(@Query("model") model:String):Call<List<CarHttp>>
}