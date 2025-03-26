package com.movie.restApi.model

import java.util.Date
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "comments")
data class Comment(
    @Id
    val id: String,
    var movieId: String,
    var user: String,
    val createdAt: Date,
    var content: String,
    val rating: Double
)