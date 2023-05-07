package com.example.help.app

import android.app.Application
import timber.log.Timber

/**
 * @author yuanan
 * @date 2023/5/7
 *
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化timber日志
        Timber.plant(Timber.DebugTree())
        Timber.d("TimberInitializer is initialized.")
    }
}