package com.prometheus.marvelproject.di.modules

import com.prometheus.marvelproject.cache.SimpleCache
import com.prometheus.marvelproject.service.MarvelService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [(SimpleCacheModule::class)])
class serviceModule {

    companion object {
        const val CONNECT_TIMEOUT: Long = 10
        const val READ_TIME_OUT: Long = 30
        const val RESPONSE_ERROR_TOKEN_AUTH: Int = 401
        const val METHOD_GET = "GET"
        const val METHOD_POST = "POST"
    }

    @Provides
    fun providesOkHttpClient(simpleCache: SimpleCache): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            var request = chain.request().newBuilder().build()
            val response: Response

                if(isValidURLToApplyCustomerId(request.url().url().path)){
                    response = when(request.method()){
                        METHOD_GET -> {
                            val url = request.url().newBuilder().build()
                            request = request.newBuilder().url(url).build()
                            chain.proceed(request)
                        }
                        METHOD_POST -> {
                            val body = request.body()
                            if(body != null){
                                val requestBody = processApplicationJsonRequestBody(body)
                                if(requestBody != null){
                                    val requestBuilder = request.newBuilder()
                                    request = requestBuilder
                                        .post(requestBody)
                                        .build()
                                    chain.proceed(request)
                                } else {
                                    chain.proceed(request)
                                }
                            } else {
                                chain.proceed(request)
                            }

                        }
                        else -> chain.proceed(request)
                    }
                } else {
                    response = chain.proceed(request)
                }

            if (response.code() == RESPONSE_ERROR_TOKEN_AUTH) {
                //TODO sessionManager.notifyLogout()
            }
            response
        }
        return httpClient
    }

    private fun isValidURLToApplyCustomerId(path: String?): Boolean {
        path?.let {
            return !path.startsWith("/config/")
                    && !path.startsWith("/customers/")
                    && !path.startsWith("/auth/")
        }
        return true
    }

    @Singleton
    @Provides
    fun providesApiService(httpClient: OkHttpClient.Builder): MarvelService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(MarvelService.BASE_URL)
            .client(httpClient.build())
            .build()
        return retrofit.create<MarvelService>(MarvelService::class.java)
    }

    private fun RequestBody?.bodyToString(): String {
        if (this == null) return ""
        val buffer = okio.Buffer()
        writeTo(buffer)
        return buffer.readUtf8()
    }

    private fun processApplicationJsonRequestBody(requestBody: RequestBody): RequestBody? {
        val customReq = requestBody.bodyToString()
        try {
            val obj = JSONObject(customReq)
            return RequestBody.create(requestBody.contentType(), obj.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }


}