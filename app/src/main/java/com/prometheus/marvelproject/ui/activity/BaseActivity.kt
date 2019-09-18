package com.prometheus.marvelproject.ui.activity


import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.cache.SimpleCache
import com.prometheus.marvelproject.service.MarvelService
import com.prometheus.marvelproject.utilities.Logger
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var marvelApiService: MarvelService
    @Inject lateinit var simpleCache: SimpleCache
    @Inject lateinit var logger: Logger
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        MarvelProjectApp.appComponent.inject(this)
    }

    override fun finish() {
        disposables.clear()
        super.finish()
    }
}