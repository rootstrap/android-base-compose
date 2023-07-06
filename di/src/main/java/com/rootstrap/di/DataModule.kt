package com.rootstrap.di

import com.rootstrap.data.store.PreferencesDataStore
import com.rootstrap.data.utils.ErrorHandler
import com.rootstrap.data.utils.ErrorNotifier
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { androidApplication().resources }
    single { ErrorNotifier() }
    single {
        PreferencesDataStore(
            coroutineDispatcher = Dispatchers.IO,
            context = androidContext(),
            preferencesFileName = "app_prefs",
        )
    }

    factory {
        ErrorHandler(
            resources = get(),
            errorNotifier = get()
        )
    }
}
