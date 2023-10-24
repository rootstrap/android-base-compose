package com.rootstrap.tv.data

import com.rootstrap.domain.HomeRow
import com.rootstrap.domain.Movie

class MoviesRepository {

    companion object {
        const val TOP_TEN = "Top 10 Movies"
        const val NOW_PLAYING = "Now playing"
        const val SCIENCE_FICTION = "Science fiction"
    }

    suspend fun getMovie(id: String): Movie {
        return Movie(
            id = "10",
            posterUri = "https://wallpaperaccess.com/full/4012394.png",
            name = "Batman Returns",
            description = "Batman Returns (titulada Batman regresa en Hispanoamérica y Batman vuelve en España y Argentina) es una película estadounidense de 1992 dirigida por Tim ."
        )
    }

    suspend fun getFeaturedMovies(): List<Movie> {
        return listOf(
            Movie(
                id = "5",
                posterUri = "https://asalallena.com.ar/wp-content/uploads/2020/07/MAW72rq-1.png",
                name = "The imitation game",
                description = "The Imitation Game is a 2014 historical drama film directed by Morten Tyldum and written by Graham Moore, based on the 1983 biography Alan Turing: The Enigma by Andrew Hodges. It stars Benedict Cumberbatch as British cryptanalyst Alan Turing, who decrypted German intelligence codes for the British government during the Second World War. Keira Knightley, Matthew Goode, Rory Kinnear, Charles Dance, and Mark Strong also star."
            ),
            Movie(
                id = "6",
                posterUri = "https://images.thedirect.com/media/article_full/new-star-wars-movie.jpg",
                name = "Star wars Episode VII",
                description = "Star wars Episode VII"
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
                )
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

    suspend fun getHomeRows(): List<HomeRow> {
        val topTenRow = HomeRow(
            name = TOP_TEN,
            rowItems = getTop10Movies()
        )
        val nowPlayingMovies = HomeRow(
            name = NOW_PLAYING,
            rowItems = getNowPlayingMovies()
        )
        val scienceFictionMovies = HomeRow(
            name = SCIENCE_FICTION,
            rowItems = getScienceFictionMovies()
        )
        return listOf(
            topTenRow,
            nowPlayingMovies,
            scienceFictionMovies
        )
    }

    suspend fun myFavourites(): List<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        for (i in 0..20) {
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
