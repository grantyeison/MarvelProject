package com.prometheus.marvelproject.service

import com.prometheus.marvelproject.BuildConfig
import com.prometheus.marvelproject.model.*
import io.reactivex.Observable
import retrofit2.http.*


interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<CharacterResult>

    @GET("/v1/public/comics")
    fun getComics(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<ComicResult>

    @GET("/v1/public/creators")
    fun getCreators(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<CreatorsResult>

    @GET("/v1/public/events")
    fun getEvents(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<EventsResult>

    @GET("/v1/public/series")
    fun getSeries(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<SeriesResult>

    @GET("/v1/public/stories")
    fun getStories(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<StoriesResult>

    companion object {
        /**
         * Api Base URL
         */
        const val BASE_URL = BuildConfig.SERVICE_URL
    }
}
