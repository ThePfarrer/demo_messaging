package com.example.demo.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
data class Note(
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: String ="",
    val title: String,
    val body: String,
    @CreationTimestamp
    val created: Date = Date(),
    @UpdateTimestamp
    var modified: Date = Date()
)
