package com.fionicholas.footballapp.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun subscribe(it: Disposable) {
        compositeDisposable.add(it)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}