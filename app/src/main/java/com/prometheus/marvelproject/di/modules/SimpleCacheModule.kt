package com.prometheus.marvelproject.di.modules

import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.cache.SimpleCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [(AppModule::class)])
class SimpleCacheModule {

    @Provides
    @Singleton
    fun providesSimpleCache(application: MarvelProjectApp): SimpleCache {
        return SimpleCache(application)
    }
}