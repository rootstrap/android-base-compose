package com.rootstrap

import android.app.Application
import com.rootstrap.di.initDI
import com.rootstrap.yourAppName.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI(appModule = appModule)
    }
}
