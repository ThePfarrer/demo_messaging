package com.example.demo.controllers

import com.example.demo.security.UserDTO
import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    lateinit var service: UserService

    @GetMapping
    fun getUsers() = service.getUsers()

    @GetMapping("/{email}")
    fun getUsers(@PathVariable email: String) = service.loadUserByUsername(email)

    @PostMapping
    fun createUser(@RequestBody user: UserDTO) {
        if (user.isAdmin) {
            service.saveAdmin(user)
        } else {
            service.saveMember(user)
        }
    }

    @PutMapping("/{email}")
    fun updateUser(@PathVariable email: String) = service.updateUser(email)

    @PutMapping("/{id}")
    fun deleteUser(@PathVariable id: String) = service.deleteUser(id)
}