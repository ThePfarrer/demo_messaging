package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore


data class CurrencyExchange(val base: String, val date: String, val rates: List<Rate>){
    companion object {
        const val BASE_CODE = "USD"
    }
}



