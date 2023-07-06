package com.example.demo.models


data class CurrencyExchange(val base: String = "USD", val date: String, val rates: List<Rate>)
