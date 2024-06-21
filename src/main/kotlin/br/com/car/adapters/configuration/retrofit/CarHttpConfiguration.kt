import br.com.car.adapters.http.CarHttpService
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class CarHttpConfiguration{
    // Equivalent to the static operator in Java
    // Constants can only be declared inside companion objects
    private companion object{
        // Host url for the API
        const val BASE_URL = "localhost:8080"
    }
    // If the API needs a token, or any type of request config, all of those are managed by the client
    private fun buildClient() = OkHttpClient.Builder().build()
    private fun buildRetroFit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildClient())
        .build()
    // Retrofit implements while in execution
    @Bean
    fun carHttpService():CarHttpService = buildRetroFit().create(CarHttpService::class.java)
}