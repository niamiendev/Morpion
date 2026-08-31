package com.berakahnd.morpion

import android.app.Application
import com.berakahnd.morpion.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MorpionApp : Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MorpionApp)
            modules(appModule)
        }
    }
}