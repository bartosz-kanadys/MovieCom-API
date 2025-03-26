package com.movie.restApi.repository

import com.movie.restApi.model.Movie
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface MovieRepository: MongoRepository<Movie, String> {
    fun findByTitleContainingIgnoreCase(title: String): List<Movie>
    override fun findById(id: String): Optional<Movie>
    fun findByTitleRegex(title: String, pageable: Pageable)
}