package com.rootstrap.tv.di

import com.rootstrap.tv.data.MoviesRepository
import org.koin.dsl.module

val dataModule = module {
    single { MoviesRepository() }
}
