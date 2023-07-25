package com.rootstrap.tv.data

import com.rootstrap.domain.Movie

class MoviesRepository {
    suspend fun getFeaturedMovies(): List<Movie> {
        return listOf(
            Movie(
                id = "5",
                posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/16_9-400/acts-of-love.jpg",
                name = "Gentleman",
                description = "The film is the sequel to Rocky III (1982) and the fourth installment in the Rocky film series. It also stars Talia Shire, Burt Young, Carl Weathers, Ton"
            ),
            Movie(
                id = "6",
                posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/16_9-400/night-in-tokyo.jpg",
                name = "Night in Tokio",
                description = "Night in Tokio"
            )
        )
    }
}
