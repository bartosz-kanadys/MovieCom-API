package com.movie.restApi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection  = "users")
data class User(
    @Id
    val id: String,
    var login: String,
    var email: String,
    var password: String,
    var role: List<String> = listOf("ROLE_USER")
)