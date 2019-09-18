package com.prometheus.marvelproject.di.modules

import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.utilities.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val application: MarvelProjectApp) {
    @Provides
    @Singleton
    fun provideContext(): MarvelProjectApp = application

    @Provides
    @Singleton
    fun provideLogger(): Logger = Logger(application)
}