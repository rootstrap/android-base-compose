package com.rootstrap.tv.pages.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootstrap.domain.Movie
import com.rootstrap.tv.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val videoRepository: MoviesRepository) : ViewModel() {
    private var _uiStateFlow: MutableStateFlow<DetailUiState> =
        MutableStateFlow(
            DetailUiState()
        )
    val uiStateFlow: StateFlow<DetailUiState> = _uiStateFlow.asStateFlow()
    private val uiState: DetailUiState
        get() = _uiStateFlow.value

    fun onDetailLoad(id: String) {
        viewModelScope.launch {
            val detail = videoRepository.getMovie(id)
            _uiStateFlow.update { uiState.copy(movie = detail) }
        }
    }
}

data class DetailUiState(
    val movie: Movie? = null
)
