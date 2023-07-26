package com.rootstrap.tv.pages.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootstrap.domain.Movie
import com.rootstrap.tv.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val videoRepository: MoviesRepository) : ViewModel() {

    companion object {
        const val TOP_TEN = "Top 10 Movies"
        const val NOW_PLAYING = "Now playing"
        const val SCIENCE_FICTION = "Science fiction"
    }

    private val uiState: MovieUiState =
        MovieUiState(SnapshotStateList(), SnapshotStateList())
    private var _uiStateFlow: MutableStateFlow<MovieUiState> =
        MutableStateFlow(
            uiState
        )
    val uiStateFlow: StateFlow<MovieUiState> = _uiStateFlow

    fun onHomeScreenLoaded() {
        viewModelScope.launch {
            val topTenRow = HomeRow(
                name = TOP_TEN,
                rowItems = videoRepository.getTop10Movies().toMutableStateList()
            )
            val nowPlayingMovies = HomeRow(
                name = NOW_PLAYING,
                rowItems = videoRepository.getNowPlayingMovies().toMutableStateList()
            )
            val scienceFictionMovies = HomeRow(
                name = SCIENCE_FICTION,
                rowItems = videoRepository.getScienceFictionMovies().toMutableStateList()
            )
            _uiStateFlow.update {
                uiState.copy(
                    homeRows = listOf(
                        topTenRow,
                        nowPlayingMovies,
                        scienceFictionMovies
                    ).toMutableStateList(),
                    featuredMovies = videoRepository.getFeaturedMovies().toMutableStateList()
                )
            }
        }
    }
}

data class MovieUiState(
    val homeRows: SnapshotStateList<HomeRow>,
    val featuredMovies: SnapshotStateList<Movie>
)
