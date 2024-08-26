package com.example.moviefinder.data.repository

import com.example.moviefinder.data.api.MovieApi
import com.example.moviefinder.data.api.RetrofitClient

class MovieRepository {
    private val api = RetrofitClient.retrofit.create(MovieApi::class.java)

    suspend fun discoverMovies(page: Int) = api.discoverMovies(page)
    suspend fun searchMovies(query: String, page: Int) = api.searchMovies(query, page)
    suspend fun getMovieDetails(id: Int) = api.getMovieDetails(id)
}