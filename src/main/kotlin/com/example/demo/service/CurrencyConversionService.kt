package com.example.demo.service

import com.example.demo.models.CurrencyConversion
import com.example.demo.models.CurrencyExchange
import com.example.demo.models.Rate
import com.example.demo.repository.RateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Stream


@Service
class CurrencyConversionService {
    @Autowired
    lateinit var repository: RateRepository
    fun convertFromTo(base: String, code: String, amount: Double): CurrencyConversion {

        val baseRate = if (base.uppercase() != CurrencyExchange.BASE_CODE) {
            repository.findByDateAndCode(Date(), base)?.rate
        } else 1.0

        val codeRate = if (code.uppercase() != CurrencyExchange.BASE_CODE) {
            repository.findByDateAndCode(Date(), code)?.rate
        } else 1.0

        return CurrencyConversion(base, code, amount, amount * (codeRate / baseRate))
    }

    fun calculateByCode(code: String, date: Date): List<Rate> {
        val rates = repository.findByDate(date)

        if (code == CurrencyExchange.BASE_CODE) {
            return rates
        }

        val baseRate =
            rates.stream().filter { it.code == code }.findFirst().orElseThrow { RuntimeException("Bad Base Code") }
//        return rates.filter { it.code != code }.map { it -> Rate(it.code, it.rate / baseRate.rate, date) }

        return Stream.concat(
            rates.stream().filter { it.code != code }.map { it -> Rate(it.code, it.rate / baseRate.rate, date) },
            Stream.of(Rate(CurrencyExchange.BASE_CODE, 1 / baseRate.rate, date))
        ).toList()
    }

    fun saveRates(rates: List<Rate>, date: Date) =
        rates.stream().forEach { r -> repository.save(Rate(r.code, r.rate, date)) }


}
