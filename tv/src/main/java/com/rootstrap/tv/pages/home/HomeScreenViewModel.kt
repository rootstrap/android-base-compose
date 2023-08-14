package com.rootstrap.tv.pages.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootstrap.domain.Movie
import com.rootstrap.tv.data.MoviesRepository
import com.rootstrap.tv.models.HomeRowModel
import com.rootstrap.tv.utils.HomeRowMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val videoRepository: MoviesRepository) : ViewModel() {
    private val uiState: MovieUiState =
        MovieUiState(SnapshotStateList(), SnapshotStateList(), SnapshotStateList())
    private var _uiStateFlow: MutableStateFlow<MovieUiState> =
        MutableStateFlow(
            uiState
        )
    val uiStateFlow: StateFlow<MovieUiState> = _uiStateFlow

    init {
        loadData()
    }
    private fun loadData() {
        viewModelScope.launch {
            val rows = HomeRowMapper.getHomeRows(videoRepository.getHomeRows())
            _uiStateFlow.update {
                uiState.copy(
                    homeRows = rows,
                    featuredMovies = videoRepository.getFeaturedMovies().toMutableStateList(),
                    favouriteRows = videoRepository.myFavourites().toMutableStateList()
                )
            }
        }
    }
}

data class MovieUiState(
    val homeRows: SnapshotStateList<HomeRowModel>,
    val favouriteRows: SnapshotStateList<Movie>,
    val featuredMovies: SnapshotStateList<Movie>
)
