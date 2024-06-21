package br.com.car.domain.http

// Used to convert the API result to an application object
// The params should be the same

// In retrofit the advantage shows up here, it isn't necessary for the data class to have all the params from the API
// if a param from the API response is not in the data class, it will be ignored.
data class CarHttp(
    val make:String,
    val model:String,
    val year:Long
)
