package com.example.demo.exception

import com.example.demo.models.CurrencyConversion
import com.example.demo.models.Rate

class BadCodeRuntimeException(override val message: String?, val conversion: CurrencyConversion?=null, val rate: Rate?=null) :
    RuntimeException() {

    companion object {
        private const val serialVersionUID = -2411444965751028974L
    }
}
