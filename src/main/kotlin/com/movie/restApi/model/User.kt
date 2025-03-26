package com.movie.restApi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "users")
data class User(
    @Id
    val id: String,
    val login: String,
    val email: String,
    val password: String,
    val role: List<String> = listOf("ROLE_USER")
)