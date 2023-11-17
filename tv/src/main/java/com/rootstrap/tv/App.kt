package com.rootstrap.tv

import android.app.Application
import com.rootstrap.tv.di.appModule
import com.rootstrap.tv.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            fragmentFactory()
            modules(appModule, dataModule)
        }
    }
}
