package com.mohamed.kataberlinclock

import android.app.Application
import com.mohamed.kataberlinclock.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BerlinClockApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BerlinClockApplication)
            modules(appModule)
        }
    }
}