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

    suspend fun getScienceFictionMovies(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        for (i in 0..10) {
            list.add(
                Movie(
                    id = i.toString(),
                    posterUri = "https://wallpaperaccess.com/full/1893778.jpg",
                    name = "Star Wars",
                    description = "La nave en la que viaja la princesa Leia es capturada por las tropas imperiales al mando del temible Darth Vader. Antes de ser atrapada, Leia consigue introducir un mensaje en su robot R2-D2, quien acompañado de su inseparable C-3PO logra escapar. "
                )
            )
        }
        return list
    }

    suspend fun getNowPlayingMovies(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        for (i in 0..10) {
            list.add(
                Movie(
                    id = i.toString(),
                    posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/cyber-net.jpg",
                    name = "Rocky II",
                    description = "The film is the sequel to Rocky III (1982) and the fourth installment in the Rocky film series. It also stars Talia Shire, Burt Young, Carl Weathers, Ton"
                )
            )
            list.add(
                Movie(
                    id = "1",
                    posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/on-the-bridge.jpg",
                    name = "Teminator",
                    description = "Terminator is an American media franchise created by James Cameron and Gale Anne Hurd. The franchise encompasses a series of science fiction action films"
                ),
            )
            list.add(
                Movie(
                    id = "2",
                    posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/inventor.jpg",
                    name = "Rocky IV",
                    description = "The film is the sequel to Rocky III (1982) and the fourth installment in the Rocky film series. It also stars Talia Shire, Burt Young, Carl Weathers, Ton"
                )
            )
        }
        return list
    }

    suspend fun getTop10Movies(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        for (i in 0..10) {
            list.add(
                Movie(
                    id = i.toString(),
                    posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/the-good-laywer.jpg",
                    name = "Teminator II",
                    description = "Terminator is an American media franchise created by James Cameron and Gale Anne Hurd. The franchise encompasses a series of science fiction action films"
                )
            )
        }
        return list
    }
}
