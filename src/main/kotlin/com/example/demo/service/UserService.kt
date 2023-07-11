package com.example.demo.service

import com.example.demo.repository.UserRepository
import com.example.demo.security.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService : UserDetailsService {
    @Autowired
    lateinit var repository: UserRepository

    val encoder = BCryptPasswordEncoder()

    override fun loadUserByUsername(email: String): User? {
        return repository.findByEmail(email) ?: throw RuntimeException("User not found: $email")
    }

    fun saveMember(user: UserDTO): User {
        val member = Member()
        member.email = user.email
        member.firstName = user.firstName
        member.lastName = user.lastName
        member.pwd = encoder.encode(user.password)
        member.roles = "MEMBER"
        return repository.save(member)
    }

    fun saveAdmin(user: UserDTO): User {
        val admin = Admin()
        admin.email = user.email
        admin.firstName = user.firstName
        admin.lastName = user.lastName
        admin.roles = "ADMIN, MEMBER"
        admin.pwd = encoder.encode(user.password)
        return repository.save(admin)
    }

    fun updateUser(email: String): User? {
        val user = repository.findByEmail(email)
        user?.let {
            if (user.pwd.isEmpty()) {
                user.pwd = encoder.encode(user.password)
            }
            user.firstName = user.firstName
            user.lastName = user.lastName
            user.accountNonExpired = user.accountNonExpired
            user.accountNonLocked = user.accountNonLocked
            user.credentialsNonExpired = user.credentialsNonExpired
            user.modified = Date()
            return repository.save(user)
        }
        return null
    }

    fun getUsers() = repository.findAll().map { it ->
        UserDetailsDTO(
            it.id,
            it.email,
            it.firstName,
            it.lastName,
            it.roles,
            it.enabled,
            it.accountNonExpired,
            it.accountNonLocked,
            it.credentialsNonExpired,
            it.created,
            it.modified
        )
    }

    fun deleteUser(id: String) = repository.deleteById(id)
}