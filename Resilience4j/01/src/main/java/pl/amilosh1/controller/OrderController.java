package pl.amilosh1.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh1.config.ExternalAPICaller;
import pl.amilosh1.dto.ServiceBDto;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final ExternalAPICaller externalAPICaller;

    public OrderController(ExternalAPICaller externalAPICaller) {
        this.externalAPICaller = externalAPICaller;
    }

    @GetMapping("/circuit-breaker-service-a")
    @CircuitBreaker(name = "serviceA", fallbackMethod = "fallbackMethodServiceA")
    public String circuitBreakerServiceA() {
        return externalAPICaller.callApi();
    }

    @GetMapping("/circuit-breaker-service-b")
    @CircuitBreaker(name = "serviceB", fallbackMethod = "fallbackMethodServiceB")
    public ServiceBDto circuitBreakerServiceB(ServiceBDto serviceBDto) {
        externalAPICaller.callApi();
        return serviceBDto;
    }

    public String fallbackMethodServiceA(Exception ex) {
        return "fallbackMethodServiceA";
    }

    public ServiceBDto fallbackMethodServiceB(ServiceBDto serviceBDto, Exception ex) {
        return serviceBDto;
    }

    @GetMapping("/retry-service-a")
    @Retry(name = "serviceA", fallbackMethod = "fallbackAfterRetryServiceA")
    @TimeLimiter(name = "serviceA")
    public String retryServiceA() {
        return externalAPICaller.callApi();
    }

    public String fallbackAfterRetryServiceA(Exception ex) {
        return "fallbackAfterRetryServiceA";
    }
}
