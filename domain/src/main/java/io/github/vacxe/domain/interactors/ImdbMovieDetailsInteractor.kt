package io.github.vacxe.domain.interactors

import io.github.vacxe.data.movie.Movie
import io.github.vacxe.domain.mappers.Mapper
import io.github.vacxe.domain.mappers.imdb.DetailsResponseToMovieMapper
import io.github.vacxe.domain.mappers.imdb.MovieDetails
import io.github.vacxe.omdbapi.api.OmdbApi
import io.github.vacxe.omdbapi.dto.MovieDetailResponse
import kotlinx.coroutines.CoroutineDispatcher

class ImdbMovieDetailsInteractor(
    dispatcher: CoroutineDispatcher,
    private val omdbApi: OmdbApi,
) : BaseInteractor<String, MovieDetails>(dispatcher) {
    private val mapper: Mapper<MovieDetailResponse, Movie> = DetailsResponseToMovieMapper()

    override suspend fun action(input: String): MovieDetails {
        val result = omdbApi.getMovieDetail(input)

        return when {
            result.error != null -> MovieDetails.Error(result.error.orEmpty())
            else -> MovieDetails.Success(mapper.map(result))
        }
    }
}
