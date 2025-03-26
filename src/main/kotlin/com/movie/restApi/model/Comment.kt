package com.movie.restApi.model

import org.springframework.data.annotation.CreatedDate
import java.util.Date
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


//Model: Serwis, repozytorium

@Document(collection = "comments")
data class Comment(
    @Id // MongoDB automatycznie generuje ID
    val id: String? = null,
    var movieId: String,
    var user: String,

    @CreatedDate // Automatyczna data utworzenia (wymaga @EnableMongoAuditing)
    val createdAt: Date? = null,
    var content: String,
    val rating: Double
)