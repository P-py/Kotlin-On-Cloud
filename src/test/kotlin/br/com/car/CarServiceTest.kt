package br.com.car

import br.com.car.adapters.bd.CarRepository
import br.com.car.adapters.http.CarHttpService
import br.com.car.core.service.CarService
import io.kotest.core.spec.style.FunSpec
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CarServiceTest : FunSpec({
    val car = CarFixture.getCar()
    // Mocks
    lateinit var carRepository:CarRepository
    lateinit var carHttpService:CarHttpService
    lateinit var carService:CarService

    // Mocking the results
    beforeTest{
        carRepository = mockk{
            every { listAll() } returns listOf(car)
            every { listByModel(any()) } returns listOf(car)
        }
        carHttpService = mockk{
            coEvery { getByModel(any()) } returns mockk()
        }
        carService = CarService(carRepository, carHttpService)
    }
    test("should return all items of specific model when carModel is not null"){
        val actual = carService.list("Gol")
        verify(exactly = 1){
            carRepository.listByModel(any())
        }
        verify(exactly = 0){
            carRepository.listAll()
        }
    }
    test("should return all items when carModel is null"){
        val actual = carService.list(null)
        verify(exactly = 0){
            carRepository.listByModel(any())
        }
        verify(exactly = 1){
            carRepository.listAll()
        }
    }
}){}