package com.fionicholas.footballapp.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.league.LeagueDataSource
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import io.reactivex.schedulers.Schedulers

class LeagueViewModel(private val repository: LeagueDataSource) : BaseViewModel() {

    private val _detailLeagues = MutableLiveData<List<DetailLeague>>().apply { value = emptyList() }
    val detailLeagues: LiveData<List<DetailLeague>> = _detailLeagues

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadDetailLeague(id : String) {
        subscribe(repository.getDetailLeague(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _detailLeagues.postValue(it)
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