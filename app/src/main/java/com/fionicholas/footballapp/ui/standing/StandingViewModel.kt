package com.fionicholas.footballapp.ui.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.standing.StandingDataSource
import com.fionicholas.footballapp.data.standing.remote.response.Standing
import io.reactivex.schedulers.Schedulers

class StandingViewModel(private val repository: StandingDataSource) : BaseViewModel() {

    private val _standing = MutableLiveData<List<Standing>>().apply { value = emptyList() }
    val standing: LiveData<List<Standing>> = _standing

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadStanding(id: String) {
        subscribe(repository.getStanding(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _standing.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    private fun onErrorNetwork(throwable: Throwable) {
        _isViewLoading.postValue(false)
        _onMessageError.postValue(throwable.message)
    }

}