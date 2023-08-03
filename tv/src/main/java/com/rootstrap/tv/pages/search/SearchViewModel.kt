package com.rootstrap.tv.pages.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {
    private var _uiStateFlow: MutableStateFlow<SearchUiState> =
        MutableStateFlow(
            SearchUiState()
        )
    val uiStateFlow: StateFlow<SearchUiState> = _uiStateFlow.asStateFlow()
    private val uiState: SearchUiState
        get() = _uiStateFlow.value

    fun onKeyPressed(key: String, type: KeyboardLetterType) {
        when (type) {
            KeyboardLetterType.AlphaNumeric -> {
                onKeyPressed(key)
            }

            KeyboardLetterType.ChangeInputType -> {
                onNumericPressed()
            }

            KeyboardLetterType.Clear -> {
                onErasePressed()
            }
        }
    }

    private fun onKeyPressed(key: String) {
        val query = uiState.query + key
        updateUI { uiState.copy(query = query) }
    }

    private fun onErasePressed() {
        val query = uiState.query
        if (query.isNotEmpty()) {
            val newQuery = uiState.query.subSequence(0, query.length - 1).toString()
            updateUI { uiState.copy(query = newQuery) }
        }
    }

    private fun onNumericPressed() {
        val newValue = !uiState.keyBoardIsNumeric
        updateUI { uiState.copy(keyBoardIsNumeric = newValue) }
    }

    private fun updateUI(function: (state: SearchUiState) -> SearchUiState) {
        _uiStateFlow.update(function)
    }
}

data class SearchUiState(
    val query: String = "",
    val keyBoardIsNumeric: Boolean = false
)

enum class KeyboardLetterType {
    AlphaNumeric,
    Clear,
    ChangeInputType
}
