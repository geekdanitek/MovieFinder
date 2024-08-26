package com.example.moviefinder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviefinder.ui.components.MovieItem
import com.example.moviefinder.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit,
    onSearch: (String) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val movies by viewModel.discoverMovies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search movies") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(
            onClick = { onSearch(searchQuery) },
            modifier = Modifier.padding(15.dp)
        ) {
            Text("Search")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(15.dp))
        } else {
            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })
                }
                item {
                    Button(
                        onClick = { viewModel.loadDiscoverMovies() },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text("Load More")
                    }
                }
            }
        }
    }
}