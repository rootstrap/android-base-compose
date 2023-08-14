package com.rootstrap.tv.di

import com.rootstrap.tv.pages.detail.MovieDetailViewModel
import com.rootstrap.tv.pages.home.HomeScreenViewModel
import com.rootstrap.tv.pages.search.SearchViewModel
import com.rootstrap.tv.player.VideoPlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MovieDetailViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { VideoPlayerViewModel() }
}
