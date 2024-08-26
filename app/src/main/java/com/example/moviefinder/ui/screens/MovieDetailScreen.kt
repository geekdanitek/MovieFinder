package com.example.moviefinder.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.moviefinder.ui.viewmodels.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = viewModel()
) {
    val movieDetail by viewModel.movieDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetail(movieId)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        movieDetail?.let { movie ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w342${movie.poster_path}",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Fit,
                    onError = { Log.e("AsyncImage", "Error loading image: ${movie.poster_path}") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = movie.title, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Release Date: ${movie.release_date}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Rating: ${movie.vote_average}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Genres: ${movie.genres.joinToString { it.name }}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = movie.overview)
            }
        }
    }
}