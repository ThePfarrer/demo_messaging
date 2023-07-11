package com.example.demo.security

data class UserDTO(
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var isAdmin: Boolean = false

    )
