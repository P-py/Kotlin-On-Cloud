package br.com.car.adapters.configuration.circuitbreaker

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerConfiguration {
    // could use .ofDefaults to use a pre-defined pattern of resilience4j
    fun getConfiguration() = CircuitBreakerConfig.custom()
        // could use .TIME_BASED
        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
        // in 10 requests, if 70% are slow calls or 70% return failure, the circuit is triggered
        .slidingWindowSize(10)
        .slowCallRateThreshold(70.0f)
        .failureRateThreshold(70.0f)
        // in case of failure, waits 5 seconds on half-open state
        .waitDurationInOpenState(Duration.ofSeconds(5))
        // sets the "slowCall" parameter to be triggered if a request takes more than 3 seconds
        .slowCallDurationThreshold(Duration.ofSeconds(3))
        // number of requests once the half-open state is active
        .permittedNumberOfCallsInHalfOpenState(3)
        .build()

    fun getCircuitBreaker() = CircuitBreakerRegistry.of(getConfiguration())
        .circuitBreaker("circuit-breaker-car-service")
}