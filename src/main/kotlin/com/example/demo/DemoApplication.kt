package com.example.demo

import com.example.demo.models.Rate
import com.example.demo.repository.RateRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
class DemoApplication {
    @Bean
    fun data(repository: RateRepository): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            repository.save(Rate("EUR", 0.88857, Date()))
            repository.save(Rate("JPY", 102.17, Date()))
            repository.save(Rate("MXN", 19.232, Date()))
            repository.save(Rate("GBP", 0.75705, Date()))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
