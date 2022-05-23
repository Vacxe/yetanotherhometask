package io.github.vacxe.omdbapi.api

import io.github.vacxe.omdbapi.dto.MovieDetailResponse
import io.github.vacxe.omdbapi.dto.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET(".")
    suspend fun getMovieList(@Query("s") search:String,
                             @Query("type") type: String,
                             @Query("page") page:Int): MovieSearchResponse

    @GET(".")
    suspend fun getMovieDetail(@Query("i") movieId:String): MovieDetailResponse
}