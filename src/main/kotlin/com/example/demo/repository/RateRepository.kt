package com.example.demo.repository

import com.example.demo.models.Rate
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RateRepository : CrudRepository<Rate, String> {
    fun findByDate(date: Date): List<Rate>
    fun findByDateAndCode(date: Date, code: String): Rate?
}