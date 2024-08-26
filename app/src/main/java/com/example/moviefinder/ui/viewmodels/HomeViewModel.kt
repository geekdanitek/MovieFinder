package com.example.moviefinder.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinder.data.models.Movie
import com.example.moviefinder.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _discoverMovies = MutableStateFlow<List<Movie>>(emptyList())
    val discoverMovies = _discoverMovies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var currentPage = 1

    init {
        loadDiscoverMovies()
    }

    fun loadDiscoverMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.discoverMovies(currentPage)
                _discoverMovies.value = response.results
                currentPage++
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}