package io.github.vacxe.domain.mappers.imdb

import io.github.vacxe.data.movie.Movie

sealed class MovieDetails {
    data class Success(val movie: Movie) : MovieDetails()
    data class Error(val message: String) : MovieDetails()
}