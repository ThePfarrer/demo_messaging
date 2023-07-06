package com.example.demo.service

import com.example.demo.models.CurrencyConversion
import com.example.demo.models.Rate
import com.example.demo.repository.RateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CurrencyConversionService {
    @Autowired
    lateinit var repository: RateRepository
    fun convertFromTo(base: String, code: String, amount: Double): CurrencyConversion {

        val baseRate = if (base.uppercase() != "USD") {
            repository.findByDateAndCode(Date(), base)?.rate ?: 1.0
        } else 1.0

        val codeRate = if (code.uppercase() != "USD") {
            repository.findByDateAndCode(Date(), code)?.rate ?: 0.0
        } else 1.0

        return CurrencyConversion(base, code, amount, amount * (codeRate / baseRate))
    }

    fun calculateByCode(code: String, date: Date): List<Rate> {
        val rates = repository.findByDate(date)
        return rates
    }
}