# Movie Finder App

## Description
Movie Finder is an Android application built with Kotlin and Jetpack Compose. It allows users to discover popular movies, search for specific titles, and view detailed information about each movie. The app uses The Movie Database (TMDb) API to fetch movie data.

## Features
- Discover popular movies
- Search for movies by title
- View detailed information about each movie
- Clean and modern UI built with Jetpack Compose

## Technologies Used
- Kotlin
- Jetpack Compose
- Retrofit for API calls
- Coil for image loading
- MVVM architecture

## Setup
1. Clone the repository
2. Create a `secrets.properties` file in the root directory with the following content: BASE_URL="https://api.themoviedb.org/3/", AUTH_TOKEN="your_tmdb_api_key_here"
3. Replace `your_tmdb_api_key_here` with your actual TMDb API key
4. Open the project in Android Studio
5. Sync the project with Gradle files
6. Run the app on an emulator or physical device

## API Reference
This app uses The Movie Database (TMDb) API. You need to sign up for an API key at [https://www.themoviedb.org/documentation/api](https://www.themoviedb.org/documentation/api)

## Screenshots
[Add screenshots of your app here]

## Future Improvements
- Add favorite movies feature
- Implement local caching for offline mode
- Add movie trailers and reviews
- Improve UI/UX with animations and transitions

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)