package io.github.vacxe.yetanotherhometask.view.list

import io.github.vacxe.omdbapi.dto.SearchItem

sealed class MovieListState {
    object Loading : MovieListState()
    data class Error(val message: String) : MovieListState()
    data class Content(val content: List<SearchItem>?) : MovieListState()
}
