package com.movie.restApi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collation = "movies")
data class Movie(
    @Id
    val id: String,
    val plot: String,
    val genres: List<String>,
    val runtime: Int,
    val cast: List<String> = emptyList(),
    val numMflixComments: Int,
    val poster: String,
    val title: String,
    val fullPlot: String,
    val languages: List<String> = emptyList(),
    val released: Date,
    val directors: List<String> = emptyList(),
    val writers: List<String> = emptyList(),
    val awards: Awards,
) {
    data class Awards(
        val wins: Int,
        val nominations: Int,
        val text: String
    )

    data class Imdb(
        val rating: Double,
        val votes: Int,
        val id: Int
    )

    data class Tomatoes(
        val viewer: Viewer,
        val website: String,
        val production: String,
        val lastUpdated: Date,
        val dvd: Date
    ) {
        data class Viewer(
            val rating: Double,
            val numReviews: Double,
            val meter: Int
        )
    }
}



