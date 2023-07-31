package com.rootstrap.tv.models

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.rootstrap.domain.Movie

data class HomeRowModel(val name: String, val rowItems: SnapshotStateList<Movie>)
