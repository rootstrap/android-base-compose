package com.rootstrap.di

import com.rootstrap.presenter.AppActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  app module provides viewModels and other objects in the app module
 *  For example: viewModel{}, single{}, factory{}
 * */
val appModule = module {
    viewModel { AppActivityViewModel(get()) }
}
