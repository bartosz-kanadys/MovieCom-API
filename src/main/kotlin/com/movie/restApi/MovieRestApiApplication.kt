package com.movie.Movie.RestApi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieRestApiApplication

fun main(args: Array<String>) {
	runApplication<MovieRestApiApplication>(*args)
}
