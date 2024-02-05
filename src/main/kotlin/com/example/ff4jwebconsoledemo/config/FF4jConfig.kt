package com.example.ff4jwebconsoledemo.config

import org.ff4j.FF4j
import org.ff4j.redis.RedisConnection
import org.ff4j.security.SpringSecurityAuthorisationManager
import org.ff4j.springjdbc.store.EventRepositorySpringJdbc
import org.ff4j.store.FeatureStoreRedis
import org.ff4j.store.PropertyStoreRedis
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource

@Configuration
class FF4jConfig(
        @Value("\${redis.host}") private val redisHost: String,
        @Value("\${redis.port}") private val redisPort: Int,
        @Value("\${spring.datasource.url}") private val jdbcUrl: String,
        @Value("\${spring.datasource.driver-class-name}") private val jdbcDriver: String,
        @Value("\${spring.datasource.username}") private val jdbcUsername: String,
        @Value("\${spring.datasource.password}") private val jdbcPassword: String
) {

    @Bean
    fun getFF4j(): FF4j {
        val jdbcConnection = DriverManagerDataSource().apply {
            url = jdbcUrl
            username = jdbcUsername
            password = jdbcPassword
            setDriverClassName(jdbcDriver)
        }

        val redisConnection = RedisConnection(redisHost, redisPort)

        val ff4j = FF4j().apply {
            featureStore = FeatureStoreRedis(redisConnection)
            propertiesStore = PropertyStoreRedis(redisConnection)
            eventRepository = EventRepositorySpringJdbc(jdbcConnection)
            authorizationsManager = SpringSecurityAuthorisationManager()
        }

        ff4j.audit(true)

        return ff4j
    }
}