package com.prometheus.marvelproject.application

import android.app.Application
import com.prometheus.marvelproject.di.components.AppComponent
import com.prometheus.marvelproject.di.components.DaggerAppComponent
import com.prometheus.marvelproject.di.modules.AppModule


class MarvelProjectApp: Application() {

    companion object {
        lateinit var instance: MarvelProjectApp
        lateinit var appComponent: AppComponent
    }

    fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeDagger()
    }
}