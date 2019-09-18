package com.prometheus.marvelproject.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    val showHideLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val onError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}