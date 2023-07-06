package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.util.*

@Entity
data class Rate(@Id val code: String = "USD", val rate: Double, @JsonIgnore @Temporal(TemporalType.DATE) val date: Date)
//data class Rate(@Id val code: String = "USD", val rate: Double, val date: Date)