package com.example.demo.security

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open var id: String = "",
    @NotNull
    @Email
    open var email: String = "",
    @NotBlank
    open var pwd: String = "",
    @NotBlank
    open var firstName: String = "",
    @NotBlank
    open var lastName: String = "",
    open var roles: String = "",
    open var enabled: Boolean = true,
    open var accountNonExpired: Boolean = true,
    open var accountNonLocked: Boolean = true,
    open var credentialsNonExpired: Boolean = true,
    @CreationTimestamp
    open var created: Date = Date(),
    @UpdateTimestamp
    open var modified: Date = Date()
) : UserDetails {

    constructor() : this(
        "", "", "", "", "",
        "", true, true, true, true, Date(), Date()
    )


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        roles.split(",").forEach { it ->
            authorities.add(SimpleGrantedAuthority(it.trim()))
        }
        return authorities
    }

    override fun getPassword(): String = pwd


    override fun getUsername(): String = email


    override fun isAccountNonExpired(): Boolean = accountNonExpired

    override fun isAccountNonLocked(): Boolean = accountNonLocked

    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired

    override fun isEnabled(): Boolean = enabled
}