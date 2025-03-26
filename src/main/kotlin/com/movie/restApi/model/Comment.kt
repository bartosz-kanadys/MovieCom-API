package com.movie.restApi.model

import java.util.Date
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "comments")
data class Comment(
    @Id
    val id: String,
    val movieId: String,
    val user: String,
    val createdAt: Date,
    val content: String,
    val rating: Double
)