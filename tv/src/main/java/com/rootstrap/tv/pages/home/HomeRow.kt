package com.rootstrap.tv.pages.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.rootstrap.domain.Movie

data class HomeRow(val name: String, val rowItems: SnapshotStateList<Movie>)
