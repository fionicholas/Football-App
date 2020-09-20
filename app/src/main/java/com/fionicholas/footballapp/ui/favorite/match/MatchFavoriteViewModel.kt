package com.fionicholas.footballapp.ui.favorite.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.matchfavorite.MatchFavoriteDataSource
import com.fionicholas.footballapp.data.matchfavorite.local.response.MatchFavorite
import io.reactivex.schedulers.Schedulers

class MatchFavoriteViewModel(private val repository: MatchFavoriteDataSource) : BaseViewModel() {

    private val _matchFavoriteList = MutableLiveData<List<MatchFavorite>>().apply { value = emptyList() }
    val matchFavoriteList: LiveData<List<MatchFavorite>> = _matchFavoriteList

    private val _matchFavoriteById = MutableLiveData<List<MatchFavorite>>().apply { value = emptyList() }
    val matchFavoriteById: LiveData<List<MatchFavorite>> = _matchFavoriteById

    private val _addMatchFavorite = MutableLiveData<Unit>().apply { Unit }
    val addMatchFavorite: LiveData<Unit> = _addMatchFavorite

    private val _deleteMatchFavorite = MutableLiveData<Unit>().apply { Unit }
    val deleteMatchFavorite: LiveData<Unit> = _deleteMatchFavorite

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadMatchFavoriteList() {
        subscribe(repository.getMatchFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _matchFavoriteList.postValue(it)
            }, {
                onError(it)
            })
        )
    }

    fun loadMatchFavoriteListById(id : Int) {
        subscribe(repository.getMatchFavoriteById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _matchFavoriteById.postValue(it)
            }, {
                onError(it)
            })
        )
    }

    fun addMatchFavorite(matchFavorite: MatchFavorite) {
        subscribe(repository.addMatchFavorite(matchFavorite)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _addMatchFavorite.postValue(Unit)
            }, {
                onError(it)
            })
        )
    }

    fun deleteMatchFavorite(id : Int) {
        subscribe(repository.deleteMatchFavorite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _deleteMatchFavorite.postValue(Unit)
            }, {
                onError(it)
            })
        )
    }

    private fun onError(throwable: Throwable) {
        _isViewLoading.postValue(false)
        _onMessageError.postValue(throwable.message)
    }
}