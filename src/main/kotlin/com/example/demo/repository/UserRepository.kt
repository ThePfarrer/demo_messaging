package com.example.demo.repository

import com.example.demo.security.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
    fun findByEmail(email: String): User?
}