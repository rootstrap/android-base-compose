package com.rootstrap

import android.app.Application
import com.rootstrap.di.appModule
import com.rootstrap.di.initDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI(appModule = appModule)
    }
}
