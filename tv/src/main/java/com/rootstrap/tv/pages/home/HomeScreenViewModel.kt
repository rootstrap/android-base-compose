package com.rootstrap.tv.pages.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootstrap.tv.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val videoRepository: MoviesRepository) : ViewModel() {
    private val uiState: MovieUiState =
        MovieUiState(SnapshotStateList())
    private var _uiStateFlow: MutableStateFlow<MovieUiState> =
        MutableStateFlow(
            uiState
        )
    val uiStateFlow: StateFlow<MovieUiState> = _uiStateFlow

    fun onHomeScreenLoaded() {
        viewModelScope.launch {
            _uiStateFlow.update {
                uiState.copy(
                    featuredMovies = videoRepository.getFeaturedMovies().toMutableStateList(),
                )
            }
        }
    }
}

data class MovieUiState(
    val featuredMovies: SnapshotStateList<com.rootstrap.domain.Movie>
)
