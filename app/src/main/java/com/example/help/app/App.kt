package com.example.help.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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
        shared = getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedEditor = shared.edit()
    }

    companion object {
        private lateinit var sharedEditor: SharedPreferences.Editor
        private lateinit var shared: SharedPreferences
        fun setString(key: String, value: String) {
            sharedEditor.putString(key, value)
            sharedEditor.commit();
        }

        fun getString(key: String): String? = shared.getString(key, null)
    }
}