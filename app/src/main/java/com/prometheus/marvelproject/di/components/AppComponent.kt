package com.prometheus.marvelproject.di.components

import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.cache.SimpleCache
import com.prometheus.marvelproject.di.modules.AppModule
import com.prometheus.marvelproject.di.modules.SimpleCacheModule
import com.prometheus.marvelproject.di.modules.serviceModule
import com.prometheus.marvelproject.service.MarvelService
import com.prometheus.marvelproject.ui.activity.BaseActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class), (serviceModule::class), (SimpleCacheModule::class)])
interface AppComponent {
    fun appContext() : MarvelProjectApp
    fun MarvelService(): MarvelService
    fun simpleCache(): SimpleCache
    fun inject(baseActivity: BaseActivity)
}