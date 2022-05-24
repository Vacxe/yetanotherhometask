package io.github.vacxe.yetanotherhometask.view.details

import io.github.vacxe.data.movie.Movie

sealed class MovieDetailsState {
    object Loading : MovieDetailsState()
    data class Error(val message: String) : MovieDetailsState()
    data class Content(val movie: Movie) : MovieDetailsState()
}
