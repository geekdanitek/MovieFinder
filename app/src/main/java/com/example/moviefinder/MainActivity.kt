package com.example.moviefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviefinder.ui.screens.HomeScreen
import com.example.moviefinder.ui.screens.MovieDetailScreen
import com.example.moviefinder.ui.screens.SearchResultsScreen
import com.example.moviefinder.ui.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MovieFinderTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
            MovieFinderApp()
        }
    }
}

@Composable
fun MovieFinderApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onTimeout = { navController.navigate("home") })
        }
        composable("home") {
            HomeScreen(
                onMovieClick = { id -> navController.navigate("movie/$id") },
                onSearch = { query -> navController.navigate("search/$query") }
            )
        }
        composable("search/{query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            SearchResultsScreen(
                query = query,
                onMovieClick = { id -> navController.navigate("movie/$id") }
            )
        }
        composable("movie/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            MovieDetailScreen(movieId = id)
        }
    }
}