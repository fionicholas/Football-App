package com.fionicholas.footballapp.ui.favorite.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.teamfavorite.TeamFavoriteDataSource
import com.fionicholas.footballapp.data.teamfavorite.local.response.TeamFavorite
import io.reactivex.schedulers.Schedulers

class TeamFavoriteViewModel(private val repository: TeamFavoriteDataSource) : BaseViewModel() {

    private val _teamFavoriteList = MutableLiveData<List<TeamFavorite>>().apply { value = emptyList() }
    val teamFavoriteList: LiveData<List<TeamFavorite>> = _teamFavoriteList

    private val _teamFavoriteById = MutableLiveData<List<TeamFavorite>>().apply { value = emptyList() }
    val teamFavoriteById: LiveData<List<TeamFavorite>> = _teamFavoriteById

    private val _addTeamFavorite = MutableLiveData<Unit>().apply { Unit }
    val addTeamFavorite: LiveData<Unit> = _addTeamFavorite

    private val _deleteTeamFavorite = MutableLiveData<Unit>().apply { Unit }
    val deleteTeamFavorite: LiveData<Unit> = _deleteTeamFavorite

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadTeamFavoriteList() {
        subscribe(repository.getTeamFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _teamFavoriteList.postValue(it)
            }, {
                onError(it)
            })
        )
    }

    fun loadTeamFavoriteListById(id : Int) {
        subscribe(repository.getTeamFavoriteById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _teamFavoriteById.postValue(it)
            }, {
                onError(it)
            })
        )
    }

    fun addTeamFavorite(teamFavorite: TeamFavorite) {
        subscribe(repository.addTeamFavorite(teamFavorite)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _addTeamFavorite.postValue(Unit)
            }, {
                onError(it)
            })
        )
    }

    fun deleteTeamFavorite(id : Int) {
        subscribe(repository.deleteTeamFavorite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _deleteTeamFavorite.postValue(Unit)
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