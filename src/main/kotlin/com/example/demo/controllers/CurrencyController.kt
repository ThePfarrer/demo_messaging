package com.example.demo.controllers

import com.example.demo.models.CurrencyConversion
import com.example.demo.models.Rate
import com.example.demo.repository.RateRepository
import com.example.demo.service.CurrencyConversionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/currency")
class CurrencyController(val rateRepository: RateRepository) {
    @Autowired
    lateinit var conversionService: CurrencyConversionService

    @GetMapping("/latest")
    fun getLatestRates(@RequestParam(name = "base", defaultValue = "USD") base: String) = rateRepository.findById(base)
//    fun getLatestRates(@RequestParam(name = "base", defaultValue = "USD") base: String) = rateRepository.findAll()

    //    @GetMapping("/{date}")
//    fun getRatesByDate(@PathVariable date: String, @RequestParam(name = "base", defaultValue = "USD") code: String) =
//        TODO()
//
    @GetMapping("/{amount}/{base}/to/{code}")
    fun conversion(
        @PathVariable amount: Double,
        @PathVariable base: String,
        @PathVariable code: String
    ): CurrencyConversion? {
        return conversionService.convertFromTo(base, code, amount)
    }

    @PostMapping("/new")
    fun addNewRates(@RequestBody rate: Rate) = rateRepository.save(rate)
}