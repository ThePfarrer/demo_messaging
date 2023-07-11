//package com.example.demo
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.crypto.factory.PasswordEncoderFactories
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//
//
//@Configuration
//class SecurityConfig {
//    private val pwEncoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
//
//    @Bean
//    fun authentication(): UserDetailsService {
//
//        val peter: UserDetails =
//            User.builder().username("peter").password(pwEncoder.encode("root")).roles("USER").build()
//        val simon: UserDetails =
//            User.builder().username("simon").password(pwEncoder.encode("root")).roles("ADMIN").build()
//
//        println("This is Peter's password ->>> ${peter.password}")
//        println("This is Simon's password ->>> ${simon.password}")
//
//        return InMemoryUserDetailsManager(peter, simon)
//    }
//
//
//
//}