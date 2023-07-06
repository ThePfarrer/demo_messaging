package com.example.demo.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Note(@Id @GeneratedValue val id: Long?, val title: String, val body: String)
