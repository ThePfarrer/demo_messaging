package com.example.demo.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Todo(
    @Id @GeneratedValue val id: Long?,
    val title: String,
    val message: String,
    val schedule: Long,
    val location: String
)
