package com.example.di

import android.app.Application
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

@KoinExperimentalAPI
fun Application.initDI(appModule: Module, scopesModule: Module = module { }) {
    startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(this@initDI)
        fragmentFactory()
        modules(appModule, dataModule, scopesModule, useCasesModule)
    }
}

val dataModule = module{

}

val useCasesModule = module {

}
