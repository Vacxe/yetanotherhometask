package io.github.vacxe.domain.mappers.imdb

import io.github.vacxe.data.movie.MovieShort
import io.github.vacxe.domain.mappers.Mapper
import io.github.vacxe.omdbapi.dto.SearchItem

internal class SearchItemToMovieShortMapper : Mapper<SearchItem, MovieShort> {
    override fun map(input: SearchItem) = MovieShort(
        imdbId = input.imdbID.orEmpty(),
        title = input.title.orEmpty(),
        poster = input.poster.orEmpty(),
        year = input.year.orEmpty()
    )
}