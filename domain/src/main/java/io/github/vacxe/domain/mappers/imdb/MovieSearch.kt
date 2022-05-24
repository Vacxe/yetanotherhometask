package io.github.vacxe.domain.mappers.imdb

import io.github.vacxe.data.movie.MovieShort

sealed class MovieSearch {
    data class Success(val movies: List<MovieShort>) : MovieSearch()
    data class Error(val message: String) : MovieSearch()
}