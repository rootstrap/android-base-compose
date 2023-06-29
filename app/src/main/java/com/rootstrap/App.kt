package com.rootstrap

import android.app.Application
import com.example.di.initDI
import com.rootstrap.di.appModule

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        initDI(appModule = appModule)
    }
}
