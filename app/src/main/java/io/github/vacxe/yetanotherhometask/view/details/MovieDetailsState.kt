package io.github.vacxe.yetanotherhometask.view.details

import io.github.vacxe.omdbapi.dto.MovieDetailResponse

sealed class MovieDetailsState {
    object Loading : MovieDetailsState()
    data class Error(val message: String) : MovieDetailsState()
    data class Content(val content: MovieDetailResponse) : MovieDetailsState()
}
