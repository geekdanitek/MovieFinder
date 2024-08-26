package com.example.moviefinder.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinder.data.models.Movie
import com.example.moviefinder.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var currentPage = 1
    private var currentQuery = ""

    fun searchMovies(query: String) {
        currentQuery = query
        currentPage = 1
        loadSearchResults()
    }

    fun loadMoreResults() {
        currentPage++
        loadSearchResults()
    }

    private fun loadSearchResults() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.searchMovies(currentQuery, currentPage)
                if (currentPage == 1) {
                    _searchResults.value = response.results
                } else {
                    _searchResults.value = _searchResults.value + response.results
                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}