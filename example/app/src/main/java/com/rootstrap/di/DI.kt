package com.rootstrap.di

import com.rootstrap.androidcomposebase.ui.AppActivityViewModel
import com.rootstrap.androidcomposebase.ui.pages.login.LogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 *  app module provides viewModels and other objects in the app module
 *  For example: viewModel{}, single{}, factory{}
 * */
val appModule = module {
    viewModelOf(::AppActivityViewModel)
    viewModelOf(::LogInViewModel)
}
