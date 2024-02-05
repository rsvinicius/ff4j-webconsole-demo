package com.example.ff4jwebconsoledemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun users(): UserDetailsService {
        val encoder = passwordEncoder()

        val user = User.builder()
                .username("user")
                .password(encoder.encode("secret"))
                .roles("USER")
                .build()
        val admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build()
        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf { it.disable() }
                .httpBasic {  }
                .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .authorizeHttpRequests { it.anyRequest().authenticated() }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}