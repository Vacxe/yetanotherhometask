package io.github.vacxe.domain.interactors

import io.github.vacxe.data.movie.MovieSearchParameters
import io.github.vacxe.data.movie.MovieShort
import io.github.vacxe.domain.mappers.Mapper
import io.github.vacxe.domain.mappers.imdb.MovieSearch
import io.github.vacxe.domain.mappers.imdb.SearchItemToMovieShortMapper
import io.github.vacxe.omdbapi.api.OmdbApi
import io.github.vacxe.omdbapi.dto.SearchItem
import kotlinx.coroutines.CoroutineDispatcher

class ImdbSearchInteractor(
    dispatcher: CoroutineDispatcher,
    private val omdbApi: OmdbApi,
) : BaseInteractor<MovieSearchParameters, MovieSearch>(dispatcher) {
    private val mapper: Mapper<SearchItem, MovieShort> = SearchItemToMovieShortMapper()

    override suspend fun action(input: MovieSearchParameters): MovieSearch {
        val result = omdbApi.getMovieList(
            input.title,
            input.type,
            input.page
        )

        return when {
            result.error != null -> MovieSearch.Error(result.error.orEmpty())
            result.search != null -> MovieSearch.Success(result.search.orEmpty().map(mapper::map))
            else -> MovieSearch.Error("Unknown error")
        }
    }
}
