package com.example.demo.event

import com.example.demo.models.CurrencyConversion
import org.springframework.context.ApplicationEvent


class CurrencyConversionEvent(
    source: Any,
    val message: String? = "",
    val conversion: CurrencyConversion
) : ApplicationEvent(source) {
}




