package io.github.vacxe.yetanotherhometask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.vacxe.data.movie.MovieSearchParameters
import io.github.vacxe.data.movie.MovieShort
import io.github.vacxe.domain.interactors.ImdbMovieDetailsInteractor
import io.github.vacxe.domain.interactors.ImdbSearchInteractor
import io.github.vacxe.domain.interactors.Interactor
import io.github.vacxe.domain.mappers.imdb.MovieDetails
import io.github.vacxe.domain.mappers.imdb.MovieSearch
import io.github.vacxe.omdbapi.BuildConfig
import io.github.vacxe.omdbapi.api.OmdbApi
import io.github.vacxe.omdbapi.api.OmdbApiFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    fun omdbApi() = OmdbApiFactory.build(BuildConfig.omdbToken)

    @Provides
    @Named("ioDispatcher")
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun imdbSearchInteractor(
        @Named("ioDispatcher") dispatcher: CoroutineDispatcher,
        omdbApi: OmdbApi
    ): Interactor<MovieSearchParameters, MovieSearch> = ImdbSearchInteractor(dispatcher, omdbApi)

    @Provides
    fun imdbMovieDetailsInteractor(
        @Named("ioDispatcher") dispatcher: CoroutineDispatcher,
        omdbApi: OmdbApi
    ): Interactor<String, MovieDetails> = ImdbMovieDetailsInteractor(dispatcher, omdbApi)
}
