package com.example.demo.controllers

import com.example.demo.models.CurrencyConversion
import com.example.demo.models.CurrencyExchange
import com.example.demo.models.Rate
import com.example.demo.repository.RateRepository
import com.example.demo.service.CurrencyConversionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.Date

@RestController
@RequestMapping("/currency")
class CurrencyController(val rateRepository: RateRepository) {
    @Autowired
    lateinit var conversionService: CurrencyConversionService

    @GetMapping("/latest")
    fun getLatestRates(@RequestParam(name = "base", defaultValue = "USD") base: String) = rateRepository.findById(base)

    @GetMapping("/{date}")
    fun getRatesByDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") date: Date,
        @RequestParam(name = "base", defaultValue = "USD") code: String
    ) = CurrencyExchange(code, SimpleDateFormat("yyyy-MM-dd").format(date), conversionService.calculateByCode(code, date))

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