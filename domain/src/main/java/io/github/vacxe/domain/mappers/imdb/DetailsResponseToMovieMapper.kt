package io.github.vacxe.domain.mappers.imdb

import io.github.vacxe.data.movie.Movie
import io.github.vacxe.domain.mappers.Mapper
import io.github.vacxe.omdbapi.dto.MovieDetailResponse

internal class DetailsResponseToMovieMapper : Mapper<MovieDetailResponse, Movie> {
    override fun map(input: MovieDetailResponse) = Movie(
        imdbId = input.imdbID.orEmpty(),
        title = input.title.orEmpty(),
        poster = input.poster.orEmpty(),
        year = input.year.orEmpty(),
        actors = input.actors.orEmpty(),
        plot = input.plot.orEmpty(),
        language = input.language.orEmpty(),
        runtime = input.runtime.orEmpty()
    )
}