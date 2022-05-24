package io.github.vacxe.data.movie

data class MovieSearchParameters(
    val title: String,
    val type: String = "movie",
    val page: Int = 1
)
