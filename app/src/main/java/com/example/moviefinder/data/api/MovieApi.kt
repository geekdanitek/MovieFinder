package com.example.moviefinder.data.api

import com.example.moviefinder.data.models.MovieDetail
import com.example.moviefinder.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun discoverMovies(@Query("page") page: Int): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetail
}