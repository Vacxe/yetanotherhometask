package io.github.vacxe.yetanotherhometask.view.list

import io.github.vacxe.data.movie.MovieShort

sealed class MovieListState {
    object Loading : MovieListState()
    data class Error(val message: String) : MovieListState()
    data class Content(val content: List<MovieShort>) : MovieListState()
}
