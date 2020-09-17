package com.fionicholas.footballapp.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.match.MatchDataSource
import com.fionicholas.footballapp.data.match.remote.response.Match
import io.reactivex.schedulers.Schedulers

class MatchViewModel(private val repository: MatchDataSource) : BaseViewModel() {

    private val _matchList = MutableLiveData<List<Match>>().apply { value = emptyList() }
    val matchList: LiveData<List<Match>> = _matchList

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadDetailLeague(id: String) {
        subscribe(repository.getMatchList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _matchList.postValue(it)
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