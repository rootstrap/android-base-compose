package com.rootstrap.tv.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.rootstrap.domain.HomeRow
import com.rootstrap.tv.models.HomeRowModel

object HomeRowMapper {
    fun getHomeRows(rows: List<HomeRow>): SnapshotStateList<HomeRowModel> {
        return rows.map {
            HomeRowModel(it.name, it.rowItems.toMutableStateList())
        }.toMutableStateList()
    }
}
